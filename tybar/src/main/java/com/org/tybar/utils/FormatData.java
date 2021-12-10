package com.org.tybar.utils;

import com.org.tybar.pojo.QstAnswer;
import com.org.tybar.service.PaperService;
import com.org.tybar.service.QstAnswerService;
import com.org.tybar.service.QuestionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Component
public class FormatData {

    @Autowired
    private PaperService paperService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private QstAnswerService qstAnswerService;
    private static FormatData formatData;
    @PostConstruct
    public void init(){
        formatData = this;
    }

    // 把字符串的 "[a,b,c]" 转为ArrayList
    public List<String> stringTolist(String array){
        String arraysub = array.substring(1,array.length()-1);
        String arrayString[] = arraysub.split(",");
        List<String> arrayList = Arrays.asList(arrayString);
        return arrayList;
    }

    // 把字符串数组变成整型数组
    public List<Integer> stringToint(String stringList){
        List<String> strings = new FormatData().stringTolist(stringList);
        List<Integer> integerList = new ArrayList<>();
        for (String item : strings){
            integerList.add(Integer.parseInt(item));
        }
        return integerList;
    }

    // 把获取的对象封装成数列
    public List<QstAnswer> qstAnswerList(String stringList){
        List<Integer> ids = new FormatData().stringToint(stringList);
        List<QstAnswer> qstAnswers = new ArrayList<>();
        for (Integer id : ids){
            System.out.println(id);
            qstAnswers.add(formatData.qstAnswerService.getQstAnserInfosById(id));
        }
        return qstAnswers;
    }
}
