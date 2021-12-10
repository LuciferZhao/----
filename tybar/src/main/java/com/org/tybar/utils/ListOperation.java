package com.org.tybar.utils;

import com.org.tybar.pojo.Paper;
import com.org.tybar.pojo.vo.SaveAnswerDataInfo;
import com.org.tybar.service.PaperService;
import com.org.tybar.service.QstAnswerService;
import com.org.tybar.service.QuestionService;
import com.org.tybar.service.UserInfoService;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@Component
public class ListOperation {

    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private PaperService paperService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private QstAnswerService qstAnswerService;
    private static ListOperation listOperation;
    @PostConstruct
    public void init(){
        listOperation = this;
    }
    // 数组去重
    public List<Object> listDistinct(List<Object> list){
        List newList = list.stream().distinct().collect(Collectors.toList());
        return newList;
    }

    // jsonArray 转换为 jsonObject
    public List<JSONObject> jsonObjects(String str){
        List<net.sf.json.JSONObject> jsonObjectList = new ArrayList<>();
        JSONArray jsonArray = JSONArray.fromObject(str);
        for (int i = 0;i<jsonArray.size();i++){
            net.sf.json.JSONObject jsonObject = jsonArray.getJSONObject(i);
            jsonObjectList.add(jsonObject);
        }
        return jsonObjectList;
    }

    // 处理答案数据
    public List<SaveAnswerDataInfo> answerDataOperation(String paperId){

        // 保存处理完的数据对象
        List<SaveAnswerDataInfo> saveAnswerDataInfos = new ArrayList<>();

        // 单选答案集合
        List<Object> answers = new ArrayList<>();
        // 保存各个选项的答案个数
        List<Object> answerNumber = new ArrayList<>();

        // 获取问卷问题的options
        Paper paper = listOperation.paperService.getPaperByPaperId(paperId);
        JSONArray questionList = JSONArray.fromObject(paper.getQuestionList());

        // 获取一张问卷的答案list
        List<String> qstAnswerList = listOperation.qstAnswerService.getQstAnswerByPaperId(paperId);

        for (int i = 0; i < questionList.size(); i++) {
            // 先遍历问题的每个对象来获取问题Id
            JSONObject qstjob = questionList.getJSONObject(i);
            String id = null;

            // 处理单选
            if (qstjob.get("type").equals("单选题") || qstjob.get("type").equals("下拉选择")) {
                id = qstjob.getString("id");
                String title = qstjob.getString("title");
                JSONArray options = (JSONArray) qstjob.get("options");

                // 把单选的答案放到一个集合
                for (int j = 0;j < qstAnswerList.size();j++){
                    String qstAnswer = qstAnswerList.get(j);
                    List<JSONObject> jsonObjects = new ListOperation().jsonObjects(qstAnswer);
                    for (JSONObject jsonObject : jsonObjects){
                        if (jsonObject.get(id) != null){
                            answers.add((String) jsonObject.get(id));
                        }
                    }
                }
//                System.out.println(radioAnswers.toString());

                // 计算每个选项答案个数
                for (int k = 0;k<options.size();k++){
                    int value = Collections.frequency((Collection<?>) answers, options.get(k));
                    answerNumber.add(value);
                }
//                System.out.println(answerNumber.toString());
                SaveAnswerDataInfo saveAnswerDataInfo = new SaveAnswerDataInfo(id,title,options,answerNumber);
                saveAnswerDataInfos.add(saveAnswerDataInfo);
                answerNumber = new ArrayList<>();
                answers = new ArrayList<>();
            }


            else if (qstjob.get("type").equals("多选题")){
                id = qstjob.getString("id");
                String title = qstjob.getString("title");
                JSONArray options = (JSONArray) qstjob.get("options");
                System.out.println(id);
                System.out.println(title);
                System.out.println(options);
                for (int j = 0;j < qstAnswerList.size();j++){
                    String qstAnswer = qstAnswerList.get(j);
                    List<JSONObject> jsonObjects = new ListOperation().jsonObjects(qstAnswer);
                    for (JSONObject jsonObject : jsonObjects){
                        if (jsonObject.get(id) != null){
                            JSONArray checkbox = (JSONArray) jsonObject.get(id);
                            for (int k=0;k<checkbox.size();k++){
                                answers.add(checkbox.get(k));
                            }
                        }
                    }
                }
//                System.out.println(answers);

                // 计算每个选项答案个数
                for (int k = 0;k<options.size();k++){
                    int value = Collections.frequency((Collection<?>) answers, options.get(k));
                    answerNumber.add(value);
                }
//                System.out.println(answerNumber.toString());
                SaveAnswerDataInfo saveAnswerDataInfo = new SaveAnswerDataInfo(id,title,options,answerNumber);
                saveAnswerDataInfos.add(saveAnswerDataInfo);
            }
            else if(qstjob.get("type").equals("下拉选择")){
//                id = qstjob.getString("id");
//                System.out.println(qstjob.getString("title"));
//                System.out.println(qstjob.get("options"));
//                System.out.println(id);
            }
            else if(qstjob.get("type").equals("多级下拉")){
//                id = qstjob.getString("id");
//                System.out.println(qstjob.getString("title"));
//                System.out.println(qstjob.get("options"));
//                System.out.println(id);
            }
        }
        return saveAnswerDataInfos;
    }
}
