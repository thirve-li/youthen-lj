package com.tenpay.util;

public class ConstantUtil {

    /**
     * 商家可以考虑读取配置文件
     */

    // app的微信开发平台应用id wxea864634067357ee
    public static String APP_ID_APP = "wxea864634067357ee";

    // app的应用对应的凭证 AppSecret
    public static String APP_SECRET_APP = "c437381168f197179270dc01fa08336c";

    // app的微信支付商户号
    public static String PARTNER_APP = "1318878101";

    // 应用对应的密钥
    // public static String APP_KEY = "c437381168f197179270dc01fa08336c";
    // 商户号对应的密钥
    // public static String PARTNER_KEY = "8934e7d15453e97507ef794cf7b0519d";// 商户号对应的密钥

    public static String TOKENURL_APP = "https://api.weixin.qq.com/cgi-bin/token";// 获取access_token对应的url
    public static String GATEURL_APP = "https://api.mch.weixin.qq.com/pay/unifiedorder";// 获取预支付id的接口url

    public static String ACCESS_TOKEN = "access_token";// access_token常量值
    public static String ERRORCODE = "errcode";// 用来判断access_token是否失效的值
    public static String SIGN_METHOD = "sha1";// 签名算法常量值
    public static String EXPIRE_ERRCODE = "42001";// access_token失效后请求返回的errcode
    public static String GRANT_TYPE = "client_credential";// 常量固定值
    public static String FAIL_ERRCODE = "40001";// 重复获取导致上一次获取的access_token失效,返回错误码

    // package常量值
    public static String packageValue =
            "bank_type=WX&body=%B2%E2%CA%D4&fee_type=1&input_charset=GBK&notify_url=http%3A%2F%2F127.0.0.1%3A8180%2Ftenpay_api_b2c%2FpayNotifyUrl.jsp&out_trade_no=2051571832&partner=1900000109&sign=10DA99BCB3F63EF23E4981B331B0A3EF&spbill_create_ip=127.0.0.1&time_expire=20131222091010&total_fee=1";
    public static String traceid = "testtraceid001";// 测试用户id

}
