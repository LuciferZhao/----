package com.org.tybar;

import com.alibaba.fastjson.JSON;
import com.org.tybar.pojo.QstAnswer;
import com.org.tybar.pojo.Paper;
import com.org.tybar.pojo.Question;
import com.org.tybar.pojo.vo.*;
import com.org.tybar.service.PaperService;
import com.org.tybar.service.QstAnswerService;
import com.org.tybar.service.QuestionService;
import com.org.tybar.service.UserInfoService;
import com.org.tybar.utils.*;
import javafx.beans.binding.ObjectExpression;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.json.JSONException;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.io.IOException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;
import java.util.stream.Collectors;

@SpringBootTest
class TybarApplicationTests {

    Scanner sc = new Scanner(System.in);
    @Autowired
    private UserInfoService userInfoService;
    @Autowired
    private PaperService paperService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private QstAnswerService qstAnswerService;


    // 测试添加问卷
    @Test
    void addPaper() throws ParseException {
        SimpleDateFormat sf = new SimpleDateFormat("yyyy-MM-dd hh:mm");
        String format = sf.format(new Date());
        Paper paper = new Paper("1", 1, "测试", "添加问卷", format, format, 1, "");
        System.out.println(paperService.addPaper(paper));
    }

    // 测试删除问卷
    @Test
    void delPaper() {
        System.out.println(paperService.deletePaperByPaperId("0"));
    }

    // 测试查询问题
    @Test
    void getQuestion() {
        String qstOption = questionService.getQuestionsByPaperId("3").get(1).getQstOption();
//        System.out.println(questionService.getQuestionsByPaperId(3).get(0).getQstOption());
        String[] questOption = qstOption.split("#");

        for (String opt : questOption) {
            System.out.println(opt);
        }
    }

    // 测试添加问题
    @Test
    void addQuestion() {
        System.out.println("请输入问卷id");
        int paperId = sc.nextInt();
        System.out.println("请输入问题类型:1-日常；2-学习；3-工作；4-八卦；5-IT；6-金融");
        int qstType = sc.nextInt();
        System.out.println("请输入问题题干");
        String qstTitle = sc.nextLine();
        System.out.println("请输入4个问题选项");
        String opt1 = sc.nextLine();
        String opt2 = sc.nextLine();
        String opt3 = sc.nextLine();
        String opt4 = sc.nextLine();
//        String qstOption = new FormatQstOption().formatQstOption(opt1,opt2,opt3,opt4);
//        Question question = new Question(paperId,qstType,qstTitle,qstOption,null);
//        System.out.println(questionService.addQuestions(question));
    }

    // 测试删除问题
    @Test
    void delQuestion() {
        for (int i = 1; i <= 19; i++) {
            System.out.println(questionService.deleteQuestionByPaperId(String.valueOf(i)));
        }
    }

    // 测试时间转换 2021-10-30T14:56 to yyyy-MM-dd hh:mm
    @Test
    void dateFormat() throws ParseException {

//        Long yesterday = new FormatDate().timeToLong("2021-11-20 08:00:00");
//        System.out.println(yesterday);
//        Long nowDateToLong = new FormatDate().timeToLong("2021-10-30 08:00:00");
//        System.out.println(nowDateToLong);
//        Long value = nowDateToLong-yesterday;
//        System.out.println(value/(1000 * 60 * 60 * 24));

        System.out.println(new FormatDate().checkDate(new Date().getTime(), "2021-11-15 15:47:00"));
    }

    // 测试获取一个问卷
    @Test
    void getPaper() {
        Paper paper = paperService.getPaperByPaperId(String.valueOf(3218737107L));
        System.out.println(paper);
    }

    // 测试更新问卷
    @Test
    void updatePaper() {
        Paper paper = new Paper("3218737107", "不测试直接过", "就是要自信", "2021-11-13 01:01:00", 0, "");
        int result = paperService.updatePaper(paper);
        if (result == 1) {
            System.out.println("更新问卷成功！");
        } else {
            System.out.println("更新问卷失败！ ");
        }
    }

    // 测试更新问题
    @Test
    void updateQuestion() {
        Question question = new Question(34, "3218737107", "多选题", "就是多选题", "[\"选项4\",\"选项5\",\"选项6\"]", "", "[]");
        int result = questionService.updateQuestion(question);
        if (result == 1) {
            System.out.println("更新问题成功！");
        } else {
            System.out.println("更新问题失败！");
        }
    }

    // 测试提交答案
    @Test
    void addQstAnswer() {
        QstAnswer qstAnswer = new QstAnswer("3218737107", 1, "[\"吃饭\",\"学习\"]");
        int result = qstAnswerService.addQstAnswer(qstAnswer);
        if (result == 1) {
            System.out.println("提交答案成功！");
        } else {
            System.out.println("提交答案失败！");
        }
    }

    @Test
    void getQstAnswerData() {

        List<Object> answer = new ArrayList<>();
        List<Object> answerData = new ArrayList<>();
        Map dataMap = new HashMap();
        List<Object> dataFinalList = new ArrayList<>();


        // 获取问卷的问题list
        Paper paper = paperService.getPaperByPaperId("3580520045");
        JSONArray questionList = JSONArray.fromObject(paper.getQuestionList());
//        System.out.println(questionList);

        // 获取一张问卷的答案list
        List<String> qstAnswerList = qstAnswerService.getQstAnswerByPaperId("3580520045");
//        System.out.println(qstAnswerList);

        for (int i = 0; i < questionList.size(); i++) {
            // 先遍历问题的每个对象来获取问题Id
            JSONObject qstjob = questionList.getJSONObject(i);
            String id = qstjob.getString("id");

            // 然后再遍历答案list
            for (int j = 0; j < qstAnswerList.size(); j++) {
                // 提取qstAnswerList里面的每个数组
                JSONArray qstAnswerJson = JSONArray.fromObject(qstAnswerList.get(j));
//              System.out.println(qstAnswerJson);

                // 根据问题id查询对应问题的答案情况
                for (int k = 0; k < qstAnswerJson.size(); k++) {
                    // 提取qstAnswerJson中特定id的值
                    if (qstAnswerJson.getJSONObject(k).get(id) != null) {
//                        System.out.println(id+":"+qstAnswerJson.getJSONObject(k).get(id));
                        // 把是符合同个问题Id的答案保存到集合
                        answer.add(qstAnswerJson.getJSONObject(k).get(id));
                    } else {
                        continue;
                    }
                }
            }
            // 把特定问题id的答案集合存起来，有多少题目，有多少人填，就会有多少的集合，每个集合多少个答案
            answerData.add(answer);
//            System.out.println("answerData"+answerData);
            answer = new ArrayList<>();

            // 遍历答案集合list
            for (int x = 0; x < answerData.size(); x++) {
                // 获取答案集合list的每个答案集合
                List getAnswer = (List) answerData.get(x);
//                System.out.println("getAnswer"+getAnswer);
                // 对答案集合进行去重
                List newAnswer = (List) getAnswer.stream().distinct().collect(Collectors.toList());
//                System.out.println(id+":"+newAnswer);
                // 遍历去重的答案集合，再将里面的答案到答案集合list里面匹配重复项，计算个数
                for (int j = 0; j < newAnswer.size(); j++) {
//                System.out.println(answerData.get(i));
                    // 计算每个选项的个数
                    int value = Collections.frequency((Collection<?>) answerData.get(x), newAnswer.get(j));
//                    System.out.println(newAnswer.get(j)+":"+value);
                    dataMap.put(x + "" + newAnswer.get(j), value);
                }
//                System.out.println(dataMap);
            }
            System.out.println(id);
            System.out.println(dataMap);
            SaveDateMap saveDateMap = new SaveDateMap("title", id, new DataAnalysis().dataMapFormat(dataMap));
            dataFinalList.add(saveDateMap);
            dataMap.clear();
            // 清空原有的答案集合list，防止重复
            answerData = new ArrayList<>();
        }

        System.out.println(dataFinalList.toString());
    }

    @Test
    void dataAnalysis() {
        List<Object> nameList = new ArrayList<>();
        List<Integer> valueList = new ArrayList<>();
        List<Object> dataList = new DataAnalysis().dataAnalysis("5704177684");
        for (Object data : dataList) {
            SaveDateMap saveDateMap = (SaveDateMap) data;
            for (Object analysis : saveDateMap.getDataList()) {
                SaveDateAnalysis saveDateAnalysis = (SaveDateAnalysis) analysis;
//               System.out.println(saveDateAnalysis.getName());
//               System.out.println(saveDateAnalysis.getValue());
                nameList.add((Object) saveDateAnalysis.getName());
                valueList.add((Integer) saveDateAnalysis.getValue());
            }
        }
    }

    @Test
    void data() {
        SaveDateAnalysis saveDateAnalysis0 = new SaveDateAnalysis(123, "学习");
        SaveDateAnalysis saveDateAnalysis1 = new SaveDateAnalysis(124, "吃饭");
        SaveDateAnalysis saveDateAnalysis2 = new SaveDateAnalysis(145, "睡觉");
        SaveDateAnalysis saveDateAnalysis3 = new SaveDateAnalysis(176, "刷剧");
        List<Object> dataList = new ArrayList<>();
        dataList.add(saveDateAnalysis0);
        dataList.add(saveDateAnalysis1);
        dataList.add(saveDateAnalysis2);
        dataList.add(saveDateAnalysis3);

        System.out.println(dataList.toString());

    }


    @Test
    void userPaperInfo() {
        List<Paper> papers = paperService.getPaperByUserId(123456);
        List<Object> indexPapers = new ArrayList<>();
        for (Paper paper : papers) {
            List<String> qstAnswers = qstAnswerService.getQstAnswerByPaperId(paper.getPaperId());
            SaveIndexPapers saveIndexPapers = new SaveIndexPapers(
                    paper.getPaperId(),
                    paper.getPaperTitle(),
                    qstAnswers.size(),
                    paper.getPaperStatus()
            );
            indexPapers.add(saveIndexPapers);
        }
        System.out.println(indexPapers.toString());
        for (Object object : indexPapers) {
            System.out.println(object);
        }
    }

    // 测试生成报表
    @Test
    void creatExcel() throws IOException {
        Paper paper = paperService.getPaperByPaperId("6566553515");
        List<String> qstAnswers = qstAnswerService.getQstAnswerByPaperId(paper.getPaperId());
        new ExcelUtil().createXlsx(paper, qstAnswers);
    }

    // 后台数据报表
    @Test
    void showData() {
        Paper paper = paperService.getPaperByPaperId("7717781328");
        JSONArray questionList = JSONArray.fromObject(paper.getQuestionList());
        List<String> qstTitles = new ArrayList<>();
        for (int i = 0; i < questionList.size(); i++) {
            // 先遍历问题的每个对象来获取问题Id
            JSONObject qstjob = questionList.getJSONObject(i);
            String qstTitle = (String) qstjob.get("title");
            qstTitles.add(qstTitle);
        }
        List<String> qstAnswers = qstAnswerService.getQstAnswerByPaperId(paper.getPaperId());
        System.out.println(qstAnswers.toString());
    }

    // 测试修改答案
    @Test
    void updateAnswer() {
        QstAnswer qstAnswer = new QstAnswer(39, "selected");
        int result = qstAnswerService.updateQstAnswer(qstAnswer);
        System.out.println(result);
    }

    // 测试复制答案
    @Test
    void copyAnswer() {
        List<QstAnswer> qstAnswerList = new FormatData().qstAnswerList("[73,74]");
        qstAnswerService.copyQstAnswer(qstAnswerList);
    }

    // 测试删除答案
    @Test
    void delAnswer() {
        List<Integer> ids = new FormatData().stringToint("[39，28]");
        int result = qstAnswerService.delQstAnswer(ids);
        System.out.println(result);
    }

    // 柱形图数据
    @Test
    void barData() {

        // 单选答案集合
        List<Object> answers = new ArrayList<>();
        // 保存各个选项的答案个数
        List<Object> answerNumber = new ArrayList<>();


        // 获取问卷问题的options
        Paper paper = paperService.getPaperByPaperId("3649822280");
        JSONArray questionList = JSONArray.fromObject(paper.getQuestionList());

        // 获取一张问卷的答案list
        List<String> qstAnswerList = qstAnswerService.getQstAnswerByPaperId("3649822280");

        for (int i = 0; i < questionList.size(); i++) {
            // 先遍历问题的每个对象来获取问题Id
            JSONObject qstjob = questionList.getJSONObject(i);
            String id = null;
            if (qstjob.get("type").equals("单选题")) {
//                id = qstjob.getString("id");
//                JSONArray options = (JSONArray) qstjob.get("options");
//                System.out.println(qstjob.getString("title"));
//                System.out.println(options);
//                System.out.println(id);
//
//                // 把单选的答案放到一个集合
//                for (int j = 0;j < qstAnswerList.size();j++){
//                    String qstAnswer = qstAnswerList.get(j);
//                    List<JSONObject> jsonObjects = new ListOperation().jsonObjects(qstAnswer);
//                    for (JSONObject jsonObject : jsonObjects){
//                        if (jsonObject.get(id) != null){
//                            answers.add((String) jsonObject.get(id));
//                        }
//                    }
//                }
////              System.out.println(Answers.toString());
//
//                // 计算每个选项答案个数
//                for (int k = 0;k<options.size();k++){
//                    int value = Collections.frequency((Collection<?>) Answers, options.get(k));
//                    answerNumber.add(value);
//                }
//
//                System.out.println(radioAnswerNumber.toString());
            }
            else if (qstjob.get("type").equals("多选题")){
//                id = qstjob.getString("id");
//                String title = qstjob.getString("title");
//                JSONArray options = (JSONArray) qstjob.get("options");
//                System.out.println(id);
//                System.out.println(title);
//                System.out.println(options);
//                for (int j = 0;j < qstAnswerList.size();j++){
//                    String qstAnswer = qstAnswerList.get(j);
//                    List<JSONObject> jsonObjects = new ListOperation().jsonObjects(qstAnswer);
//                    for (JSONObject jsonObject : jsonObjects){
//                        if (jsonObject.get(id) != null){
//                            JSONArray checkbox = (JSONArray) jsonObject.get(id);
//                            for (int k=0;k<checkbox.size();k++){
//                                answers.add(checkbox.get(k));
//                            }
//                        }
//                    }
//                }
////                System.out.println(answers);
//
//                // 计算每个选项答案个数
//                for (int k = 0;k<options.size();k++){
//                    int value = Collections.frequency((Collection<?>) answers, options.get(k));
//                    answerNumber.add(value);
//                }
//                System.out.println(answerNumber.toString());
            }
            else if(qstjob.get("type").equals("下拉选择")){
                id = qstjob.getString("id");
                String title = qstjob.getString("title");
                JSONArray options = (JSONArray) qstjob.get("options");
                System.out.println(id);
                System.out.println(title);
                System.out.println(options);
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
              System.out.println(answers.toString());

                // 计算每个选项答案个数
                for (int k = 0;k<options.size();k++){
                    int value = Collections.frequency((Collection<?>) answers, options.get(k));
                    answerNumber.add(value);
                }

                System.out.println(answerNumber.toString());
            }
            else if(qstjob.get("type").equals("多级下拉")){
//                id = qstjob.getString("id");
//                System.out.println(qstjob.getString("title"));
//                System.out.println(qstjob.get("options"));
//                System.out.println(id);
            }
        }
    }

    @Test
    void testDataOperation(){
        List<SaveAnswerDataInfo> saveAnswerDataInfos = new ListOperation().answerDataOperation("3649822280");
        System.out.println(saveAnswerDataInfos.toString());
    }
}
