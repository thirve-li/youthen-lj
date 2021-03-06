package com.youthen.lj.constant;

public interface AppConfig {

    /**
     * 发送验证码长度
     */
    public static final int VERIFY_CODE_LENGTH = 4;

    /**
     * 注册送积分
     */
    public final int SCORE_REG = 100;

    /**
     * 登录送积分
     */
    public final int SCORE_LOGIN = 10;

    /**
     * 签到送积分
     */
    public final int SCORE_SIGN = 10;

    /**
     * 应用ID， 在微信公众平台中 “开发者中心”栏目可以查看到
     * wx80a9e5ffbb1ea9a4
     */
    public final String ID_APP = "wxea864634067357ee";
    public final String ID_WEBCHAT = "wx80a9e5ffbb1ea9a4";

    /**
     * 应用密钥， 在微信公众平台中 “开发者中心”栏目可以查看到
     */
    public static String SECRET_APP = "da016c8db6de76af5195715f59a39da5";
    public static String SECRET_WEBCHAT = "96a6cd18b1ab9e214aa09f86ee1b1287";

    /**
     * App 微信支付商户号
     */
    public final String MERCHANT_ID_APP = "1318878101";
    public final String MERCHANT_ID_WEBCHAT = "1311324201";

    /**
     * API密钥，在微信商户平台中“账户设置”--“账户安全”--“设置API密钥”，只能修改不能查看
     */
    public static String API_KEY_APP = "Y4L9akbl3adknKm6ayRvaE0aqsCx7ued";
    public static String API_KEY_WEBCHAT = "y4L9akbl3adknKm6ayRvaE0aqsCx7ueD";

    /**
     * 统一下单
     */
    public static String GATEURL = "https://api.mch.weixin.qq.com/pay/unifiedorder";

    /**
     * 支付后的回调地址URL
     */
    final String NOTIFY_URL = "http://www.elinnuan.com/ljwy-web/payNotify.do";
    final String NOTIFY_URL_WEBCHAT = "http://www.elinnuan.com/ljwy-web/wxpayNotify.do";

    /**
     * 快递接口授权密匙(Key)
     */
    public static String APP_KUAIDI_KEY = "zRdRXwXf1446";

    /**
     * 实时快递查询接口的公司编号
     */
    public static String APP_KUAIDI_COM = "99F71E48649BF786D0EDD8053B0298AF";

    public static final String SECRET_KEY = "mobileSecretKey1234567890";
    public static final String MANAGER_MEMORY = "ManagerMemory";
    public final int MONEY_PARK = 100;
    public final String ACTION_LOGIN = "登录";
    public final String ACTION_SIGN = "签到";
    public final String ACTION_REG = "注册";
}
