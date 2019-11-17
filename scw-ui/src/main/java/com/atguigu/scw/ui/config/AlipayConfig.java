package com.atguigu.scw.ui.config;

import java.io.FileWriter;
import java.io.IOException;

/* *
 *类名：AlipayConfig
 *功能：基础配置类
 *详细：设置帐户有关信息及返回路径
 *修改日期：2017-04-05
 *说明：
 *以下代码只是为了方便商户测试而提供的样例代码，商户可以根据自己网站的需要，按照技术文档编写,并非一定要使用该代码。
 *该代码仅供学习和研究支付宝接口使用，只是提供一个参考。
 */

public class AlipayConfig {
	
//↓↓↓↓↓↓↓↓↓↓请在这里配置您的基本信息↓↓↓↓↓↓↓↓↓↓↓↓↓↓↓

	// 应用ID,您的APPID，收款账号既是您的APPID对应支付宝账号
	public static String app_id = "2016101700705675";
	
	// 商户私钥，您的PKCS8格式RSA2私钥
    public static String merchant_private_key = "MIIEvAIBADANBgkqhkiG9w0BAQEFAASCBKYwggSiAgEAAoIBAQDV/nIY/q+TlQbiV3+IEl6yXOEPre/7KzYUD2KLnJtsJYAxKfMtsmGdKtjTaTZ+LuxVE9Qbi7MLuHeL/xDCTo7r7H4vpcwIBNTOK0pBAGk6YVlIc6vd/HEp3XHV+ZWMasXOI0JJRQnFGUmDMQcx1TNyxuO+VdHph3wlqov6aIH38HjilT07SGH0JSPRbkejzeDRfNj8vkKJ/uTwewm/sIdQnc7Hf08oCWM+2cDhgGZmTatN+4Ar9jmkUEVoeCruo3u00ZXsDWoFmkSG2u7LSr0SPDgBx4O7klDcPEYbgCNtrEa7GcAI3yJXe55w+eURKIj2Tfo72o9M6mdrLnWsfCzhAgMBAAECggEASlYfcItYlp3YTFw00yH+f1tAW2AC02+JU6Kciv8WBpVoWKvH7AHhYuV1eMs9wMuwEUXYEtGOmu2zqZArVDRR9aarlwWzQL3c5tmm67mm1D7o8wArneuJrA9e8gPHbYXRvPAGoMk4wZ0tKkOnyinTu3VXu9C4ILWN7M+33H7dVzExGqOi8W9B1lOC0/Yw/3NjIlxoyBYBGC/1C+4JFU61k9kFIosdOqXx+s61fXlutbQaBVRIHRvCDyKaO4x2bluAk+tGSRXHAjyyQFRmBU9ODRbbdPURU6ERR92jeMSDFSgyZ7hn9g3cm1DaiWM14g5/asb8u7cEkiAyngvox3P9mQKBgQD2NDU1yxrco2nU4tpunELacuCocSkY4FJ5Htp7yIpWq18hgO2VZDxje7TePa2aAIFz1PI0I5UbwHo1rRHH5OlP9ZB7JCR7dzk8LAznfoHg5cshl6d89+MCwAMcX0Rr5fy5EnN2OrbeDZo4qNdDVZSzLQk57hc38PadwbNEXUzfQwKBgQDegibutcI1TUDLSIZX2k/e1eaHFHfvdRSxIiDHgFxs2BZXgUO2JYzZ+J3mH6+q4BCiJdI0HE6BMcFmil1Yo3eojH8sf6weZDyYB5uxUz8ptqzy7YyDcHfyol0nag5hIN79L5+cyXs/CQa7a3VKHwXGEvZymBJd5A0aTql3kwFHCwKBgFEgVOQ738cSRb4zXhySeuj2b5axawRabnKguweMxiDW6Url6+ONGuUBD1En67tSLVXYtlJ94xU48Ewdkj1W7ZY+aXRaLXdNituL/FiPdAaCUdPo3/Sqze/LC3te3PHc39CwBsHPiRBUVRwUbpIucgg0b9TZ4ma5jNngyMxfHgR3AoGAV0y3W7cCYIlT364vz24/Ig3Iab01JUVJfgoG1F9fY/EQKvrzhxaKgcbSpMRP1vocbJHsPDfS8UuFNucYortYKlAdYWciL7ZiM7/gbR1dTc93YcSj+bn5xb/ATsOEqsUL2EiEFi7S5TN0abpKhW9Qg5tTCDj0E9Ry85xjTx/lQ2sCgYBUUkFdJZ2cSXnOang7a9dvoBanfsZa+J2CHK1XPhQjydWO/3A1X7nb0OqNtr1QCvWqAiL7cJvTK/oiCRh2YkdM1fJLzzWZ+qqeNtFElrMrHp3S+u2TTrVMsnha17EdKGYCOmwt2qszJw16HJeoFYK/KdFk9xsuXzSv2kLfqQIjHw==";
	
	// 支付宝公钥,查看地址：https://openhome.alipay.com/platform/keyManage.htm 对应APPID下的支付宝公钥。
    public static String alipay_public_key = "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEAnW+0FRauVPEsZNn8Kcebx+DY7spIfgCufq7zKkjrZqbUfxyUlh2n/wgNn99b/Km0OFcJpk5cnSUYOPMv4Y5X5xj7WnlKa6dkl+h/thIGyIQiAIQz8ODeSvWNViUzbYT4CTlGSOvEE7zzJPTUrweKCA9iGJEYveTgcmI6fRFXDHVVa7EyAbuFukSxphy68gCKEU3IEywdslJHKacOfEM2tlkaUoz+mpTiom+QD0JGwMlUjwyDzJB/rMFQJfrLGcZw7maKx1dsLxsuqcYo2kVZtjIfAUxiQKKO6sdwYBv5Jf2rMVm3BnoOfvZ8Sv3mcA9mHy8D5RFZWVocIFx8obDaFwIDAQAB";

	// 服务器异步通知页面路径  需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String notify_url = "http://k27728c482.zicp.vip/order/notify_url";

	// 页面跳转同步通知页面路径 需http://格式的完整路径，不能加?id=123这类自定义参数，必须外网可以正常访问
	public static String return_url = "http://k27728c482.zicp.vip/order/return_url";

	// 签名方式
	public static String sign_type = "RSA2";
	
	// 字符编码格式
	public static String charset = "utf-8";
	
	// 支付宝网关
	public static String gatewayUrl = "https://openapi.alipaydev.com/gateway.do";
	
	// 支付宝网关
	public static String log_path = "C:\\";


//↑↑↑↑↑↑↑↑↑↑请在这里配置您的基本信息↑↑↑↑↑↑↑↑↑↑↑↑↑↑↑

    /** 
     * 写日志，方便测试（看网站需求，也可以改成把记录存入数据库）
     * @param sWord 要写入日志里的文本内容
     */
    public static void logResult(String sWord) {
        FileWriter writer = null;
        try {
            writer = new FileWriter(log_path + "alipay_log_" + System.currentTimeMillis()+".txt");
            writer.write(sWord);
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            if (writer != null) {
                try {
                    writer.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}

