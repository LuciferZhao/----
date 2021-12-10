package com.org.tybar.utils;

import com.org.tybar.pojo.Paper;
import com.org.tybar.pojo.QstAnswer;
import com.org.tybar.pojo.Question;
import net.sf.json.JSONArray;
import net.sf.json.JSONObject;
import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.*;
import org.apache.poi.xssf.usermodel.XSSFWorkbook;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 使用POI的API的步骤
 * 1、创建工作簿Workbook
 * 2、创建表单Sheet
 * 3、创建文件流
 * 4、通过流的形式写出文件
 * 5、关闭流
 */

public class ExcelUtil {
    // 1、创建
    Workbook workbook;
    // 2、创建表单Sheet
    Sheet sheet;
    // 3、创建行对象
    Row row; // 从0开始，所以最后在第6行
    // 4、创建列对象
    Cell cell; // 同理

    public void createCellStyle(Workbook workbook,Sheet sheet,Row row,Cell cell,int i){
        /**
         * 样式
         */
        // 6、设置样式
        // 创建单元格样式对象
        CellStyle cellStyle = workbook.createCellStyle();
        // 设置边框
//        cellStyle.setBorderTop(BorderStyle.THIN); // 上边框
//        cellStyle.setBorderRight(BorderStyle.THIN); // 右边框
//        cellStyle.setBorderBottom(BorderStyle.THIN); // 下边框
//        cellStyle.setBorderLeft(BorderStyle.THIN); // 左边框
        // 设置字体
        Font font = workbook.createFont(); // 创建字体对象
        font.setFontName("微软雅黑"); // 设置字体
        font.setFontHeightInPoints((short) 15); // 设置字号
        cellStyle.setFont(font);
        // 设置宽高
        sheet.setColumnWidth(i,20*256); // 设置第一列的宽度是20个字符宽度
        row.setHeightInPoints(20); // 设置行的高度是50个点
        // 设置居中显示
        cellStyle.setAlignment(HorizontalAlignment.CENTER); // 水平居中
        cellStyle.setVerticalAlignment(VerticalAlignment.CENTER); // 垂直居中
        // 设置单元格样式
        cell.setCellStyle(cellStyle);
    }

    public void createXlsx(Paper paper, List<String> qstAnswers) throws IOException {
        // 1、创建
        workbook = new XSSFWorkbook();
        // 2、创建表单Sheet
        sheet = workbook.createSheet(paper.getPaperTitle());
        /**
         * 设置问卷标题
         */
        // 3、创建行对象，第一行为问卷标题
        row = sheet.createRow(0); // 和数组一样，从0开始算起的，0就是第一行
        // 将string的问卷list转为数列
        JSONArray questionList = JSONArray.fromObject(paper.getQuestionList());
        List<String> qstIds = new ArrayList<>();
        for (int i=0;i<questionList.size();i++) {
            // 先遍历问题的每个对象来获取问题Id
            JSONObject qstjob = questionList.getJSONObject(i);
            String qstTitle = (String) qstjob.get("title");
            String qstId = qstjob.getString("id");
            qstIds.add(qstId);
            // 4、创建列对象
            cell = row.createCell(i); // 同理
            // 5、创建数据
            cell.setCellValue(qstTitle);
            // 6、设置样式
            new ExcelUtil().createCellStyle(workbook,sheet,row,cell,i);
        }

        for (int x=0;x<qstAnswers.size();x++){
            row = sheet.createRow(x+1);
            // 提取qstAnswerList里面的每个数组
            JSONArray qstAnswer = JSONArray.fromObject(qstAnswers.get(x));
//            System.out.println(qstAnswer);
            for (int y=0;y<qstAnswer.size();y++) {
                cell = row.createCell(y); // 同理
                // 先遍历问题的每个对象来获取问题Id
                JSONObject answerJob = qstAnswer.getJSONObject(y);
//                System.out.println(answerJob);
                for (String id : qstIds){
                    Object answer = answerJob.get(id);
                    if (answer != null){
//                        System.out.println(answer.toString());
                        cell.setCellValue(answer.toString());
                    }else{
                        continue;
                    }
                }
            }
        }

        String path = "D:\\Software\\IntelliJ IDEA\\workspace\\tybar\\src\\main\\resources\\excel\\"+paper.getPaperTitle()+".xlsx";
        // 7、创建文件流
        FileOutputStream fileOutputStream = new FileOutputStream(path);
        // 8、通过流的形式写出文件
        workbook.write(fileOutputStream);
        // 9、关闭流
        fileOutputStream.close();
    }


}
