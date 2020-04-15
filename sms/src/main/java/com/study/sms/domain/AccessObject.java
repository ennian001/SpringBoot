package com.study.sms.domain;

import lombok.Data;

@Data
public class AccessObject {

    private String access_token;
    private Long expires_in;
    private Integer errcode;
    private String errmsg;

}
