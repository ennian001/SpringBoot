package com.study.sms.domain;

import lombok.Data;


@Data
public class SendMsgObject {

    private String touser ;
    private String template_id;
    private ParamData data;

}
