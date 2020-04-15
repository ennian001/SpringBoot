package com.study.sms.domain;

import lombok.Data;

import java.util.Map;
@Data
public class ParamData {

        private Map<String,String> first;
        private Map<String,String> keyword1;
        private Map<String,String> result;

}
