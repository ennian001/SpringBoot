package com.springbootfile.example.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;

@Controller
public class UploadControler {

    @GetMapping
    public String index() {
        return "login";
    }

    @GetMapping("/down")
    public String down() {
        return "download";
    }



    /**
     * 文件上传
     *
     * @param desc       文件描述
     * @param uploadFile 文件
     * @return
     */
    @PostMapping("/upload")
    public String upload(@RequestParam("desc") String desc, @RequestParam("uploadFile") MultipartFile uploadFile) throws IOException {

        //获取上传文件的名字
        String originalFilename = uploadFile.getOriginalFilename();
        //获取输入流
        InputStream inputStream = uploadFile.getInputStream();
        FileOutputStream os = new FileOutputStream("/opt/images/java/" + originalFilename);

        //写文件
        int i;
        while ((i = inputStream.read()) != -1) {
            os.write(i);
        }

        inputStream.close();
        os.close();

        return "sucess";
    }
}
