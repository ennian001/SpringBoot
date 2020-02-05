package com.spring.cache.test3;
import com.alibaba.fastjson.JSONObject;
import org.springframework.util.StringUtils;

import java.io.BufferedWriter;
import java.io.FileWriter;
import java.io.IOException;

public class Main {
    public static void main(String[] args) {
        String json = "{\"MSG\":{\"Message\":{\"Version\":\"V3.0.0\",\"Format\":\"JSON\",\"Merchant\":{\"ECMerchantType\":\"EBUS\",\"MerchantID\":\"103882401990457\"},\"TrxResponse\":{\"ReturnCode\":\"0000\",\"ErrorMessage\":\"交易成功\",\"TrxType\":\"SettlePOSThirdPartyStatement\",\"FileDate\":\"2019/11/20\",\"DetailRecords\":\"归属省市代码|!会计日期|!商户编号|!日志号|!卡号|!交易代码|!货币代码|!交易金额|!费用编号|!分润/分成编号_计算|!分润/分成编号_记账|!商户结算手续费|!结算折扣率|!已收/应收标识|!分期手续费|!线上线下标识|!商户类别代码|!特殊计费类型|!特殊计费档次|!交易渠道|!交易通道|!卡种|!可售产品代码|!交易跨度编号|!接入类型标识|!发卡方省市代码|!发卡方行号|!终端号|!交易行号|!外部请求流水号|!交易状态|!冲正交易日志号|!撤销标识|!累计退货金额|!关联会计日期|!关联交易日志号|!可清除日期|!交易日期|!交易时间戳|!备注|!商E付商户号|!商E付二级商户号^^24|!20191120|!113530151220026|!365894202  |!6222021901014843112|!PSPBO006|!01 |! 0000000000000000.01|!110|!1111  |!2113  |! 0000000000000000.00|! 001.00000|!1|! 0000000000000000.00|!1|!5122|!00|!0|!POSP|!BCAC|!3|!      |!8|!1|!00|!0000  |!YNp1JnLL |!240901|!2400PTWF354950046158|!N|!0          |!1|! 0000000000000000.00|!20191120|!366998667  |!        |!20191120|!20191120134003540677      |!PSBMO005                                                                                            |!103882401990457|!01test9000015^^24|!20191120|!113530151220026|!366998667  |!6222021901014843112|!PSPBO008|!01 |! 0000000000000000.01|!110|!1111  |!2113  |! 0000000000000000.00|! 001.00000|!1|! 0000000000000000.00|!1|!5122|!00|!0|!POSP|!BCAC|!3|!      |!8|!1|!00|!0000  |!YNp1JnLL |!240901|!2400PTWF254950040727|!N|!0          |!0|! 0000000000000000.00|!20191120|!365894202  |!        |!20191120|!20191120134148482791      |!PSBMO006                                                                                            |!103882401990457|!01test9000015^^24|!20191120|!113530151220026|!370449379  |!5187100014286233   |!PSPBO006|!01 |! 0000000000000000.01|!120|!1112  |!2113  |! 0000000000000000.00|! 001.00000|!1|! 0000000000000000.00|!1|!5122|!00|!0|!POSP|!BCAC|!4|!      |!8|!1|!00|!0000  |!YNp1JnLL |!240901|!2400PTWF214950041460|!N|!0          |!0|! 0000000000000000.01|!        |!0          |!        |!20191120|!20191120134839393164      |!PSBMO005                                                                                            |!103882401990457|!01test9000015^^24|!20191120|!113530151220026|!370913370  |!5187100014286233   |!PSPBO007|!01 |! 0000000000000000.01|!120|!1112  |!2113  |! 0000000000000000.00|! 001.00000|!1|! 0000000000000000.00|!1|!5122|!00|!0|!POSP|!BCAC|!4|!      |!8|!1|!00|!0000  |!YNp1JnLL |!240901|!2400PTWF304950047232|!N|!0          |!0|! 0000000000000000.00|!20191120|!370449379  |!        |!20191120|!20191120134937470782      |!PSBMO007                                                                                            |!103882401990457|!01test9000015^^24|!20191120|!113530151220026|!371514572  |!6226890111055868   |!PSPBO006|!01 |! 0000000000000000.01|!120|!1112  |!2113  |! 0000000000000000.00|! 001.00000|!1|! 0000000000000000.00|!1|!5122|!00|!0|!POSP|!BCAC|!4|!      |!8|!1|!00|!0000  |!YNp1JnLL |!240901|!2400PTWF274950041702|!N|!0          |!0|! 0000000000000000.01|!        |!0          |!        |!20191120|!20191120135050991448      |!PSBMO005                                                                                            |!103882401990457|!01test9000015^^24|!20191120|!113530151220026|!371913530  |!6226890111055868   |!PSPBO007|!01 |! 0000000000000000.01|!120|!1112  |!2113  |! 0000000000000000.00|! 001.00000|!1|! 0000000000000000.00|!1|!5122|!00|!0|!POSP|!BCAC|!4|!      |!8|!1|!00|!0000  |!YNp1JnLL |!240901|!2400PTWF294950041802|!N|!0          |!0|! 0000000000000000.00|!20191120|!371514572  |!        |!20191120|!20191120135141114925      |!PSBMO007                                                                                            |!103882401990457|!01test9000015\"}},\"Signature-Algorithm\":\"SHA1withRSA\",\"Signature\":\"HBY5dX+Nteimgxs++m6Q/5+kAik20p7YE27eTu3xO+k3QKKkj7BKrRiC59/8qSnslJOFSzfj8pvYNbIMJKdJg8Os4uoiA/kSP7lO/zSVkFi1do1HenIzVBa5QCNwAwOib1addYCtVzQkxbXhTod8qFE1IEn2/j4GSMvtcxN1SSw=\",\"SettleEnd\":\"\"}}\n" +
                "\n";
        JSONObject JSON_RESULT = (JSONObject)JSONObject.parse(json);
        JSONObject JSON_MSG = JSON_RESULT.getJSONObject("MSG");
        JSONObject MESSSAGE = JSON_MSG.getJSONObject("Message");
        JSONObject TRX_RESPONSE = MESSSAGE.getJSONObject("TrxResponse");
        if ("0000".equals(TRX_RESPONSE.getString("ReturnCode"))){
            String text = TRX_RESPONSE.getString("DetailRecords");
            String alen = new String();
            char[] chars = text.toCharArray();

            for (int i = 0; i <chars.length ; i++) {
                if (chars[i]=='^'){
                    if (!StringUtils.isEmpty(alen)) {
                        write(alen);
                    }
                    alen = "";
                    continue;
                }
                alen = alen+(chars[i]=='!'?"":chars[i]);
            }


        }


    }

    private static void write(String alen) {
        String[] split = alen.split("\\|");
        System.out.println(split.length);
        String test = new String();
        for (int i = 0; i < split.length; i++) {
            test+=split[i];
        }
        System.out.println(test);
        BufferedWriter bw = null;
        try {
            bw = new BufferedWriter(new FileWriter("test.txt",true));
            bw.write(alen+"\n");
        } catch (IOException e) {
            e.printStackTrace();
        }finally {
            if (bw!=null){
                try {
                    bw.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
