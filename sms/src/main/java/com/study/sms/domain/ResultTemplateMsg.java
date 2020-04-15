package com.study.sms.domain;

import lombok.Data;

import java.util.List;

@Data
public class ResultTemplateMsg {
    private List<Template> template_list;
}
