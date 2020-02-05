package com.springbootfile.example.controller;

import com.springbootfile.example.util.CSVUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * 下载文件的方法
 */
@Slf4j
@RestController
@RequestMapping("/downLoad")
public class DownLoadController {


    //下载csv文件
    @GetMapping("/CSV")
    public String downLoadCSV(HttpServletResponse response) throws IOException {
        String [] head = {"用户名","用户Id","交易金额"};
        String fileName = "测试下载";
        String [] value = {"王小虎","wangxiaohu1","200"};
        String [] value1 = {"王小虎","wangxiaohu1","200"};
        String [] value2 = {"王小虎","wangxiaohu1","200"};
        List<String[]> values = new ArrayList<>();
        values.add(value);
        values.add(value1);
        values.add(value2);
        values.add(value);
        File file = CSVUtils.makeTempCsv(fileName, head, values);
        response.setCharacterEncoding("utf-8");
        response.setContentType("multipart/form-data");
        //指定文件名称
//        response.setHeader("Content-Disposition", "attachment;fileName="+"test"+".csv");
        //设置文件名称为中文
        response.setHeader("Content-Disposition", "attachment;fileName="+new String("测试文件".getBytes(),"iso-8859-1")+".csv");
        //使用response下载文件
        CSVUtils.downloadFile(response, file);
        return null;
    }







}
