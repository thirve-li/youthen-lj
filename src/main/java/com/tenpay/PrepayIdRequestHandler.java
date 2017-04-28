package com.tenpay;

import java.io.UnsupportedEncodingException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.apache.log4j.Logger;
import org.json.JSONException;
import com.tenpay.client.TenpayHttpClient;
import com.tenpay.util.ConstantUtil;
import com.tenpay.util.MD5Util;
import com.tenpay.util.TenpayUtil;
import com.youthen.lj.constant.AppConfig;

public class PrepayIdRequestHandler extends RequestHandler {

    private static Logger logger = Logger.getLogger(PrepayIdRequestHandler.class.getName());

    public PrepayIdRequestHandler(final HttpServletRequest request,
            final HttpServletResponse response) {
        super(request, response);
    }

    /**
     * 创建签名SHA1
     * 
     * @param signParams
     * @return
     * @throws Exception
     */
    public String createSHA1Sign() {
        // logger.info("=====================> begin createSHA1Sign");

        System.out.println("=====================> begin createSHA1Sign ");

        final StringBuffer sb = new StringBuffer();
        final Set es = super.getAllParameters().entrySet();
        final Iterator it = es.iterator();
        while (it.hasNext()) {
            final Map.Entry entry = (Map.Entry) it.next();
            final String k = (String) entry.getKey();
            final String v = (String) entry.getValue();
            if (null != v && !"".equals(v)
                    && !"sign".equals(k) && !"key".equals(k)) {
                sb.append(k + "=" + v + "&");
            }
        }

        sb.append("key=" + AppConfig.API_KEY);
        // logger.info("params------>" + sb);

        System.out.println("=====================> sb:" + sb);

        final String enc = TenpayUtil.getCharacterEncoding(this.request, this.response);
        final String sign = MD5Util.MD5Encode(sb.toString(), enc).toUpperCase();

        // logger.info("appsign------>" + sign);
        System.out.println("=====================> appsign:" + sign);

        return sign;
    }

    // 提交预支付
    public String sendPrepay() throws JSONException {

        // //logger.info("=====================> begin sendPrepay");

        System.out.println("=====================> begin sendPrepay");
        final String prepayid = "";
        final SortedMap map = super.getAllParameters();
        final String params = "   <xml>"
                + "<appid>" + map.get("appid") + "</appid>"
                + "<mch_id>" + map.get("mch_id") + "</mch_id>"
                + "<device_info>" + map.get("device_info") + "</device_info>"
                + "<nonce_str>" + map.get("nonce_str") + "</nonce_str>"
                + "<sign>" + map.get("sign") + "</sign>"
                + "<body>" + map.get("body") + "</body>"
                + "<detail>" + map.get("detail") + "</detail>"
                // + " <attach>" + map.get("attach") + "</attach>"
                + "<out_trade_no>" + map.get("out_trade_no") + "</out_trade_no>"
                + "<fee_type>" + map.get("fee_type") + "</fee_type>"
                + "<total_fee>" + map.get("total_fee") + "</total_fee>"
                + "<spbill_create_ip>" + map.get("spbill_create_ip") + "</spbill_create_ip>"
                // + "<time_start>" + map.get("time_start") + "</time_start>"
                // + "<time_expire>" + map.get("time_expire") + "</time_expire>"
                // + "<goods_tag>" + map.get("goods_tag") + "</goods_tag>"
                + "<notify_url>" + map.get("notify_url") + "</notify_url>"
                + "<trade_type>" + map.get("trade_type") + "</trade_type>"
                // + " <limit_pay>" + map.get("limit_pay") + "</limit_pay>"
                + " </xml>";
        // //logger.info("params==>" + params);
        System.out.println("=====================> params " + params);
        final String requestUrl = super.getGateUrl();
        // //logger.info("requestUrl:" + requestUrl);

        System.out.println("=====================> requestUrl " + requestUrl);

        final TenpayHttpClient httpClient = new TenpayHttpClient();
        httpClient.setReqContent(requestUrl);
        String resContent = "";
        // logger.info("post data:" + params);
        System.out.println("post data:" + params);
        if (httpClient.callHttpPost(requestUrl, params)) {
            resContent = httpClient.getResContent();
            // logger.info("post data:" + "resContent:" + resContent);

            try {

                final String s = new String(resContent.getBytes("gbk"), "utf-8");
                System.out.println(requestUrl + " 的返回结果 " + s);
                logger.info("返回结果" + resContent);
            } catch (final UnsupportedEncodingException e) {
                e.printStackTrace();
                return e.getMessage();
            }

            return resContent;

        }
        return null;
    }

    // 判断access_token是否失效
    public String sendAccessToken() {
        String accesstoken = "";
        final StringBuffer sb = new StringBuffer("{");
        final Set es = super.getAllParameters().entrySet();
        final Iterator it = es.iterator();
        while (it.hasNext()) {
            final Map.Entry entry = (Map.Entry) it.next();
            final String k = (String) entry.getKey();
            final String v = (String) entry.getValue();
            if (null != v && !"".equals(v) && !"appkey".equals(k)) {
                sb.append("\"" + k + "\":\"" + v + "\",");
            }
        }
        String params = sb.substring(0, sb.lastIndexOf(","));
        params += "}";

        final String requestUrl = super.getGateUrl();
        // this.setDebugInfo(this.getDebugInfo() + "\r\n" + "requestUrl:"
        // + requestUrl);
        final TenpayHttpClient httpClient = new TenpayHttpClient();
        httpClient.setReqContent(requestUrl);
        String resContent = "";
        // this.setDebugInfo(this.getDebugInfo() + "\r\n" + "post data:" + params);
        if (httpClient.callHttpPost(requestUrl, params)) {
            resContent = httpClient.getResContent();
            if (2 == resContent.indexOf(ConstantUtil.ERRORCODE)) {
                accesstoken = resContent.substring(11, 16);// 获取对应的errcode的值
            }
        }
        return accesstoken;
    }
}
