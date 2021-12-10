package com.org.tybar.utils;

import java.util.*;

public class CodeSnippet {

    /**
     * 获取答案特定id
     * @return
     */
//        // 遍历答案list
//        for (int i=0;i<qstAnswerList.size();i++){
//            // 提取qstAnswerList里面的每个数组
//            JSONArray qstAnswerJson = JSONArray.fromObject(qstAnswerList.get(i));
////            System.out.println(qstAnswerJson);
//            for (int j=0;j<qstAnswerJson.size();j++){
//                // 提取qstAnswerJson中特定id的值
//                if (qstAnswerJson.getJSONObject(j).get("6739575562") != null){
//                    System.out.println(qstAnswerJson.getJSONObject(j).get("6739575562"));
//                }else{
//                    continue;
//                }
//            }
//        }
// ==============================================================================================
    /**
     *  读取集合
     */
//        System.out.println(paperService.getPaperByPaperId(req.getParameter("paperId")));
//        if (paperService.getPaperByPaperId(req.getParameter("paperId")) != null){
//            List<Question> questions = JSON.parseArray(req.getParameter("qstList"),Question.class);
//            for (Question question : questions){
//                question.setPaperId(req.getParameter("paperId"));
//                questionService.addQuestion(question);
//            }
//        }
// ===============================================================================================
//        /**
//         * 遍历问题list
//         */
////        // 将string的问卷list转为数列
//        JSONArray questionList = JSONArray.fromObject(paper.getQuestionList());
//        for (int i=0;i<questionList.size();i++) {
//            // 先遍历问题的每个对象来获取问题Id
//            JSONObject qstjob = questionList.getJSONObject(i);
//            String id = qstjob.getString("id");
//            qstIds.add(id);
//        }
////        for (String id : qstIds){
////            System.out.println(id);
////        }
//        /**
//         * 遍历答案list
//         */
//        for (int x=0;x<qstAnswers.size();x++){
//            // 提取qstAnswerList里面的每个数组
//            JSONArray qstAnswer = JSONArray.fromObject(qstAnswers.get(x));
//            System.out.println(qstAnswer);
//            for (int y=0;y<qstAnswer.size();y++) {
//                // 先遍历问题的每个对象来获取问题Id
//                JSONObject answerJob = qstAnswer.getJSONObject(y);
//                for (String id : qstIds){
//                    if (answerJob.get(id) != null){
//                        System.out.println(answerJob.get(id));
//                    }else{
//                        continue;
//                    }
//                }
//            }
//        }
}
