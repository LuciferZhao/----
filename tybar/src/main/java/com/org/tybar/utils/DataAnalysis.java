package com.org.tybar.utils;

import com.org.tybar.pojo.Paper;
import com.org.tybar.pojo.vo.SaveDateAnalysis;
import com.org.tybar.pojo.vo.SaveDateMap;
import com.org.tybar.service.PaperService;
import com.org.tybar.service.QstAnswerService;
import com.org.tybar.service.QuestionService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.*;
import java.util.stream.Collectors;

@Component
public class DataAnalysis {

    @Autowired
    private PaperService paperService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private QstAnswerService qstAnswerService;
    private static DataAnalysis dataAnalysis;
    @PostConstruct
    public void init(){
        dataAnalysis = this;
    }

    /**
     * 根据key给map排序
     * @param param
     * @return
     */
    public Map<String, String> sortMap(Map<String, String> param) {
        TreeMap<String, String> paramTreeMap = new TreeMap<>(param);
        return paramTreeMap;
    }

    /**
     * 根据paperId查询答案
     * 对答案进行对应问题编号的答案选项进行分类和统计
     * @param paperId
     * @return
     */
    public List<Object> dataAnalysis(String paperId){

        List<Object> answer = new ArrayList<>();
        List<Object> answerData = new ArrayList<>();
        Map dataMap = new HashMap();
        List<Object> dataFinalList = new ArrayList<>();


        // 获取问卷的问题list
        Paper paper = dataAnalysis.paperService.getPaperByPaperId(paperId);
        JSONArray questionList = JSONArray.fromObject(paper.getQuestionList());
//        System.out.println(questionList);

        // 获取一张问卷的答案list
        List<String> qstAnswerList = dataAnalysis.qstAnswerService.getQstAnswerByPaperId(paperId);
//        System.out.println(qstAnswerList);

        for (int i=0;i<questionList.size();i++){
            // 先遍历问题的每个对象来获取问题Id
            JSONObject qstjob = questionList.getJSONObject(i);
            String id = qstjob.getString("id");
            String title = qstjob.getString("title");

            // 然后再遍历答案list
            for (int j=0;j<qstAnswerList.size();j++){
                // 提取qstAnswerList里面的每个数组
                JSONArray qstAnswerJson = JSONArray.fromObject(qstAnswerList.get(j));
//              System.out.println(qstAnswerJson);

                // 根据问题id查询对应问题的答案情况
                for (int k=0;k<qstAnswerJson.size();k++){
                    // 提取qstAnswerJson中特定id的值
                    if (qstAnswerJson.getJSONObject(k).get(id) != null){
//                        System.out.println(id+":"+qstAnswerJson.getJSONObject(k).get(id));
                        // 把是符合同个问题Id的答案保存到集合
                        answer.add(qstAnswerJson.getJSONObject(k).get(id));
                    }else{
                        continue;
                    }
                }
            }
            // 把特定问题id的答案集合存起来，有多少题目，有多少人填，就会有多少的集合，每个集合多少个答案
            answerData.add(answer);
//            System.out.println("answerData"+answerData);
            answer = new ArrayList<>();

            // 遍历答案集合list
            for (int x=0;x<answerData.size();x++){
                // 获取答案集合list的每个答案集合
                List getAnswer = (List) answerData.get(x);
//                System.out.println("getAnswer"+getAnswer);
                // 对答案集合进行去重
                List newAnswer = (List) getAnswer.stream().distinct().collect(Collectors.toList());
//                System.out.println(id+":"+newAnswer);
                // 遍历去重的答案集合，再将里面的答案到答案集合list里面匹配重复项，计算个数
                for (int j=0;j<newAnswer.size();j++){
//                    System.out.println(answerData.get(i));
                    // 计算每个选项的个数
                    int value = Collections.frequency((Collection<?>) answerData.get(x), newAnswer.get(j));
//                    System.out.println(newAnswer.get(j)+":"+value);
                    dataMap.put(newAnswer.get(j),value);
                }
//                System.out.println(dataMap);
            }
//            System.out.println(id);
//            System.out.println(dataMap);
            // 保存一个id 对应的答案选项及其数量
            SaveDateMap saveDateMap = new SaveDateMap(title,id,new DataAnalysis().dataMapFormat(dataMap));
            // 将保存的一个id和对应答案及数量存到数列
            dataFinalList.add(saveDateMap);
            // 然后把第一个id的dataMap清空，重新再存下个id对应的答案选项
            dataMap.clear();
            // 清空原有的答案集合list，防止重复
            answerData = new ArrayList<>();
        }
//        System.out.println(dataFinalList.toString());
        return dataFinalList;
    }

    /**
     * 格式化dataMap
     * 将key 和 value 存在SaveDateAnalysis中
     * 然后再把SaveDateAnalysis的对象存到数列里面
     * @param dataMap
     * @return
     */
    public List<Object> dataMapFormat(Map dataMap){
        List<Object> dataList = new ArrayList<>();
        for(Object key : dataMap.keySet()) {
//            System.out.println(key+":"+dataMap.get(key));
            SaveDateAnalysis saveDateAnalysis = new SaveDateAnalysis(dataMap.get(key),key);
            dataList.add(saveDateAnalysis);
        }
        return dataList;
    }

    /**
     * 柱形图数据分析
     */
    public void barData(){
        Paper paper = dataAnalysis.paperService.getPaperByPaperId("3776186441");
        JSONArray questionList = JSONArray.fromObject(paper.getQuestionList());
        System.out.println(questionList);
    }

}
