package com.org.tybar.controller;

import com.org.tybar.config.Result;
import com.org.tybar.pojo.Paper;
import com.org.tybar.pojo.QstAnswer;
import com.org.tybar.pojo.vo.SaveIndexPapers;
import com.org.tybar.pojo.vo.SavePaperStatement;
import com.org.tybar.service.EmailService;
import com.org.tybar.service.PaperService;
import com.org.tybar.service.QstAnswerService;
import com.org.tybar.service.QuestionService;
import com.org.tybar.utils.FormatDate;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("/tybar/paper")
public class PaperController {

    @Autowired
    private EmailService emailService;
    @Autowired
    private PaperService paperService;
    @Autowired
    private QuestionService questionService;
    @Autowired
    private QstAnswerService qstAnswerService;

    /**
     * 用户问卷信息
     */
    @GetMapping("/indexPapers/{userId}")
    @ResponseBody
    public Result indexPapers(@PathVariable int userId){

        List<Paper> papers = paperService.getPaperByUserId(userId);
        List<Object> indexPapers = new ArrayList<>();
        for (Paper paper : papers){
            List<String> qstAnswers = qstAnswerService.getQstAnswerByPaperId(paper.getPaperId());
            SaveIndexPapers saveIndexPapers =new SaveIndexPapers(
                    paper.getPaperId(),
                    paper.getPaperTitle(),
                    qstAnswers.size(),
                    paper.getPaperStatus()
            );
            indexPapers.add(saveIndexPapers);
        }
        return Result.success("success",indexPapers);
    }

    // 查询所有问卷
    @GetMapping("/plist")
    @ResponseBody
    public Result papers(){
        List<Paper> papers = paperService.getPaperList();
        return Result.success("success",papers);
    }

    // 获取一张问卷
    @GetMapping("/onepaper/{paperId}")
    @ResponseBody
    public Result getOnePaper(@PathVariable String paperId){

        Paper paper = paperService.getPaperByPaperId(paperId);
        String message = null;
        if(paper.getPaperStatus() == 0){
            message="unstart";
        }else{
            if (new FormatDate().checkDate(new Date().getTime(),paper.getPaperEndDate()) >= 0){
                message="ending";
            }else {
                message="success";
            }
        }
        return Result.success(message,paper);
    }

    // 问卷报表
    @GetMapping("/paperStatement/{papderId}")
    @ResponseBody
    public Result paperStatement(@PathVariable String papderId){
        Paper paper = paperService.getPaperByPaperId(papderId);
        JSONArray questionList = JSONArray.fromObject(paper.getQuestionList());
        List<String> qstTitles = new ArrayList<>();
        for (int i=0;i<questionList.size();i++) {
            // 先遍历问题的每个对象来获取问题Id
            JSONObject qstjob = questionList.getJSONObject(i);
            String qstTitle = (String) qstjob.get("title");
            qstTitles.add(qstTitle);
        }
        List<QstAnswer> qstAnswers = qstAnswerService.getQstAnswerInfos(papderId);
        SavePaperStatement savePaperStatement = new SavePaperStatement(qstTitles,qstAnswers);
        return Result.success("success",savePaperStatement);
    }

    // 添加问卷
    @PostMapping("/addpaper")
    @ResponseBody
    public Result addPaper(HttpServletRequest req, HttpServletResponse resp) throws ParseException {
        Paper paper = new Paper(
                req.getParameter("paperId"),
                Integer.parseInt(req.getParameter("userId")),
                req.getParameter("paperTitle"),
                req.getParameter("paperSummary"),
                new FormatDate().formatDate1(new Date()),
                new FormatDate().formatDate2(req.getParameter("paperEndDate")),
                Integer.parseInt(req.getParameter("paperStatus")),
                req.getParameter("questionList")

        );
        paperService.addPaper(paper);
        System.out.println(req.getParameter("questionList"));
        return Result.success("success");
    }

    // 更新问卷
    @PostMapping("/updatepaper")
    @ResponseBody
    public Result updatePaper(HttpServletRequest req, HttpServletResponse resp) throws ParseException {
        Paper paper = new Paper(
                req.getParameter("paperId"),
                req.getParameter("paperTitle"),
                req.getParameter("paperSummary"),
                new FormatDate().formatDate2(req.getParameter("paperEndDate")),
                Integer.parseInt(req.getParameter("paperStatus")),
                req.getParameter("questionList")
        );
        paperService.updatePaper(paper);
        System.out.println(req.getParameter("questionList"));
        return Result.success("success");
    }

    // 更新问卷为发布状态
    @GetMapping("/publishStatus/{paperId}")
    @ResponseBody
    public Result publishStatus(@PathVariable String paperId){
        paperService.updateStatusPublishById(paperId);
        return Result.success("success");
    }

    // 更新问卷为保存状态
    @GetMapping("/saveStatus/{paperId}")
    @ResponseBody
    public Result saveStatus(@PathVariable String paperId){
        paperService.updateStatusSaveById(paperId);
        return Result.success("success");
    }

    // 删除问卷
    @GetMapping("/delpaper/{paperId}")
    @ResponseBody
    public Result delPaper(@PathVariable String paperId){
        System.out.println(paperId);
        int result = paperService.deletePaperByPaperId(paperId);
        if (result != 0){
            return Result.success();
        }else {
            return Result.fail();
        }
    }
}
