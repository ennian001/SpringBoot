package com.study.sms.domain;

import lombok.Data;

@Data
public class IndustrayMsg {
    /**模板Id**/
    private String template_id_short;

    private String errcode;

    private String errmsg;

    private String msgid;


}
