package com.springbootfile.example.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.multipart.commons.CommonsMultipartResolver;

@Configuration
public class FileConfig {


    /**
     * 配置文件上传
     */
//    @Bean/*(name = "multipartResolver") *///必须指定这个
////    public CommonsMultipartResolver commonsMultipartResolver(){
////        CommonsMultipartResolver resolver = new CommonsMultipartResolver();
////        resolver.setDefaultEncoding("utf-8");
////        resolver.setMaxUploadSize(10485760);
////        return resolver;
////    }





}
