package com.study.sms.domain;

import lombok.Data;

@Data
public class Template {
    private String template_id;
    private String title;
    private String primary_industry;
    private String deputy_industry;
    private String content;
    private String example;
}
