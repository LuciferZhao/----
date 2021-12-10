package com.org.tybar.controller;

import com.org.tybar.config.Result;
import com.org.tybar.pojo.Paper;
import com.org.tybar.pojo.QstAnswer;
import com.org.tybar.pojo.vo.SavePaperQstData;
import com.org.tybar.service.PaperService;
import com.org.tybar.service.QstAnswerService;
import com.org.tybar.utils.DataAnalysis;
import com.org.tybar.utils.ExcelUtil;
import com.org.tybar.utils.FormatData;
import com.org.tybar.utils.ListOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Collections;
import java.util.List;

@Controller
@RequestMapping("/tybar/qstanswer")
public class QstAnswerController {

    @Autowired
    private PaperService paperService;
    @Autowired
    private QstAnswerService qstAnswerService;

    @PostMapping("/addAnswer")
    @ResponseBody
    public Result addQstAnswer(HttpServletRequest req) {
        QstAnswer qstAnswer = new QstAnswer(
                req.getParameter("paperId"),
                Integer.parseInt(req.getParameter("userId")),
                req.getParameter("selected")

        );
        int result = qstAnswerService.addQstAnswer(qstAnswer);
        if (result == 1) {
            return Result.success("提交问卷成功");
        } else {
            return Result.fail("提交问卷失败");
        }
    }

    // 数据分析
    @GetMapping("/dataAnalysis/{paperId}")
    @ResponseBody
    public Result dataAnalysis(@PathVariable String paperId) {
        List<Object> dataList = new DataAnalysis().dataAnalysis(paperId);
        Paper paper = paperService.getPaperByPaperId(paperId);
        return Result.success("success", new SavePaperQstData(Collections.singletonList(dataList),paper.getQuestionList()));
    }

    // 新数据分析
    @GetMapping("/showData/{paperId}")
    @ResponseBody
    public Result showData(@PathVariable String paperId){
        return Result.success("success",new ListOperation().answerDataOperation(paperId));
    }

    // 导出excel表
    @GetMapping("/downExcel/{paperId}")
    @ResponseBody
    public void downExcel(@PathVariable String paperId, HttpServletResponse response) throws IOException {

        Paper paper = paperService.getPaperByPaperId(paperId);
        List<String> qstAnswers = qstAnswerService.getQstAnswerByPaperId(paper.getPaperId());
        new ExcelUtil().createXlsx(paper, qstAnswers);

        // 下载本地文件
        String fileName = paper.getPaperTitle()+".xlsx".toString(); // 文件的默认保存名
        // 读到流中
        InputStream inStream = new FileInputStream("D:\\Software\\IntelliJ IDEA\\workspace\\tybar\\src\\main\\resources\\excel\\"+fileName);// 文件的存放路径
        // 设置输出的格式
        response.reset();
        response.setContentType("bin");
        response.addHeader("Content-Disposition","attachment; filename="+java.net.URLEncoder.encode(fileName, "UTF-8"));
        // 循环取出流中的数据
        byte[] b = new byte[100];
        int len;
        try {
            while ((len = inStream.read(b)) > 0)
                response.getOutputStream().write(b, 0, len);
                inStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
    }

    // 修改答案
    @PostMapping("/updateAnswer")
    @ResponseBody
    public Result updateAnswer(HttpServletRequest req){
        QstAnswer qstAnswer = new QstAnswer(
                Integer.parseInt(req.getParameter("answerId")),
                req.getParameter("selected")
        );
        int result = qstAnswerService.updateQstAnswer(qstAnswer);
        if (result != 0){
            return Result.success();
        }else {
            return Result.fail();
        }
    }

    // 复制答案
    @PostMapping("/copyAnswer")
    @ResponseBody
    public Result copyAnswer(@RequestParam("answerIdList") String answerIdList){
        List<QstAnswer> qstAnswerList = new FormatData().qstAnswerList(answerIdList);
        int result = qstAnswerService.copyQstAnswer(qstAnswerList);
        if (result != 0){
            return Result.success();
        }else {
            return Result.fail();
        }
    }

    // 删除答案
    @PostMapping("/delAnswer")
    @ResponseBody
    public Result delAnswer(@RequestParam("answerIdList") String answerIdList){
        System.out.println(answerIdList);
        List<Integer> ids = new FormatData().stringToint(answerIdList);
        int result = qstAnswerService.delQstAnswer(ids);
        if (result != 0){
            return Result.success();
        }else {
            return Result.fail();
        }
    }
}
