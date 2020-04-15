package com.study.sms.service;

import com.alibaba.fastjson.JSONObject;
import com.study.sms.config.WxConfig;
import com.study.sms.domain.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.dao.DataAccessException;
import org.springframework.data.redis.connection.RedisConnection;

import org.springframework.data.redis.core.RedisCallback;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.data.redis.serializer.RedisSerializer;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.client.RestTemplate;
import redis.clients.jedis.Protocol;
import redis.clients.jedis.util.SafeEncoder;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.TimeUnit;

@Slf4j
@Service
public class WxSmsService {

    @Autowired
    WxConfig wxConfig;

    @Autowired
    StringRedisTemplate stringRedisTemplate;

    @Autowired
    RestTemplate restTemplate;

    /**
     * todo 此方法用于获取 access_token
     *
     * access_token是公众号的全局唯一接口调用凭据，公众号调用各接口时都需使用access_token。
     * 开发者需要进行妥善保存。access_token的存储至少要保留512个字符空间。
     * access_token的有效期目前为2个小时，需定时刷新，重复获取将导致上次获取的access_token失效。
     * @return
     */
    public String getAccessToken(){
        String accessToken = stringRedisTemplate.opsForValue().get("accessToken");
        if (StringUtils.isEmpty(accessToken)) {
            String accessTokenUrl = wxConfig.getAccessTokenUrl();
            String getAccessTokenUrl = String.format(accessTokenUrl, wxConfig.getAppId(), wxConfig.getAppsecret());
            AccessObject forObject = restTemplate.getForObject(getAccessTokenUrl, AccessObject.class);
            log.info(forObject.toString());
            if (forObject != null) {
                stringRedisTemplate.opsForValue().set("accessToken", forObject.getAccess_token(), 7200, TimeUnit.SECONDS);
                accessToken  = forObject.getAccess_token();
            }
        }
        return accessToken;
    }

    /**
     * 设置所属行业
     */
    public void setApiIndustry(){
        String url = "https://api.weixin.qq.com/cgi-bin/template/api_set_industry?access_token="+this.getAccessToken();
        log.info(url);
        Map<String,String> paramMap = new HashMap<>();
        paramMap.put("industry_id1","1");
        paramMap.put("industry_id2","4");
        String s = JSONObject.toJSONString(paramMap);
        Object object = restTemplate.postForObject(url, s, Object.class);
        System.out.println(object);
    }

    /**
     * 模板配置
     *  https://www.jianshu.com/p/eb0e9c4dcdfe
     *  {{first.DATA}}
     *  ......:{{xxx.DATA}}
     * 发送模板消息
     * @return
     */
    public IndustrayMsg sendTemplateMsg(String openId){
        String url = "https://api.weixin.qq.com/cgi-bin/message/template/send?access_token="+getAccessToken();
        SendMsgObject sendMsgObject = new SendMsgObject();
        sendMsgObject.setTemplate_id("rpWoXnwMRtFRsTVA3_ZQEe-ZBv0REyIez-4FT22WaZU");
        sendMsgObject.setTouser(openId);
        ParamData paramData = new ParamData();
        Map<String,String> first = new HashMap<>();
        first.put("value","heiheihei");
        first.put("color","#173177");
        Map<String,String> keyword1 = new HashMap<>();
        keyword1.put("value","heiheihei");
        keyword1.put("color","#173177");
        paramData.setFirst(first);
        paramData.setKeyword1(keyword1);
        sendMsgObject.setData(paramData);
        String s = JSONObject.toJSONString(sendMsgObject);
        log.info(s);
        IndustrayMsg industrayMsg = restTemplate.postForObject(url, s, IndustrayMsg.class);
        return industrayMsg;
    }

    /**
     * 获取模板id
     * @param template_id_short  模板库中模板的编号
     * @return
     */
    public  String applyTemplateID(String template_id_short){
        String request_url = "https://api.weixin.qq.com/cgi-bin/template/api_add_template?access_token=" + this.getAccessToken();
        Map<String,String> map = new HashMap<String,String>();
        map.put("template_id_short",template_id_short);
        String s = JSONObject.toJSONString(map);
        IndustrayMsg industrayMsg = restTemplate.postForObject(request_url, s, IndustrayMsg.class);
        log.info(industrayMsg.toString());
        return industrayMsg.getTemplate_id_short();
    }

    /**
     * 获取模板列表
     * @return
     */
    public ResultTemplateMsg getTemplateListExample(){
        String url = "https://api.weixin.qq.com/cgi-bin/template/get_all_private_template?access_token="+this.getAccessToken();
        ResultTemplateMsg forObject = restTemplate.getForObject(url, ResultTemplateMsg.class);
        return forObject;
    }



    public boolean lock(final String key , final long ex){
        Boolean b = (Boolean) stringRedisTemplate.execute(new RedisCallback<Boolean>() {
            @Override
            public Boolean doInRedis(RedisConnection connection) throws DataAccessException {
                RedisSerializer valueSerializer = stringRedisTemplate.getValueSerializer();
                RedisSerializer keySerializer = stringRedisTemplate.getKeySerializer();
                Object obj = connection.execute("set", keySerializer.serialize(key),
                        valueSerializer.serialize("lock"),
                        SafeEncoder.encode("NX"),
                        SafeEncoder.encode("EX"),
                        Protocol.toByteArray(ex));
                return obj != null;
            }

        });
        return b;
    }

    public static String map2jsonstr(Map<String,?> map){
        return JSONObject.toJSONString(map);
    }
}
