package com.study.sms;

import com.study.sms.domain.IndustrayMsg;
import com.study.sms.domain.ResultTemplateMsg;
import com.study.sms.service.WxSmsService;
import org.junit.jupiter.api.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@SpringBootTest
@RunWith(SpringRunner.class)
class SmsApplicationTests {

    @Autowired
    WxSmsService wxSmsService;

    @Test
    void contextLoads() {
        String accessToken = wxSmsService.getAccessToken();
        System.out.println(accessToken);
    }

    @Test
    void setApiIndustry() {
        wxSmsService.setApiIndustry();
    }

    // 1297317936436314113
    @Test
    void sendTemplateMsg() {
        IndustrayMsg industrayMsg = wxSmsService.sendTemplateMsg("oe7xOw0xLTstVH6TkK4itpBTxfzU");
        System.out.println(industrayMsg.toString());
    }

    @Test
    void setTemplateId() {
        String tm00015 = wxSmsService.applyTemplateID("TM00015");
        System.out.println(tm00015);
    }
    @Test
    void getTemplateListExample() {
        ResultTemplateMsg templateListExample = wxSmsService.getTemplateListExample();
        System.out.println(templateListExample.toString());
    }
}
