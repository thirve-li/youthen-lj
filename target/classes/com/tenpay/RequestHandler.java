package com.tenpay;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.net.URLEncoder;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import com.tenpay.util.MD5Util;
import com.tenpay.util.TenpayUtil;

/**
 * ��������
 * ��������̳д��࣬��дcreateSign�������ɡ�
 * 
 * @author miklchen
 */
public class RequestHandler {

    /** ����url��ַ */
    private String gateUrl;

    /** ��Կ */
    private String key;

    /** ����Ĳ��� */
    private final SortedMap parameters;

    /** debug��Ϣ */
    private String debugInfo;

    protected HttpServletRequest request;

    protected HttpServletResponse response;

    /**
     * ���캯��
     * 
     * @param request
     * @param response
     */
    public RequestHandler(final HttpServletRequest request, final HttpServletResponse response) {
        this.request = request;
        this.response = response;

        this.gateUrl = "https://gw.tenpay.com/gateway/pay.htm";
        this.key = "";
        this.parameters = new TreeMap();
        this.debugInfo = "";
    }

    /**
     * ��ʼ��������
     */
    public void init() {
        // nothing to do
    }

    /**
     * ��ȡ��ڵ�ַ,����������ֵ
     */
    public String getGateUrl() {
        return this.gateUrl;
    }

    /**
     * ������ڵ�ַ,����������ֵ
     */
    public void setGateUrl(final String gateUrl) {
        this.gateUrl = gateUrl;
    }

    /**
     * ��ȡ��Կ
     */
    public String getKey() {
        return this.key;
    }

    /**
     * ������Կ
     */
    public void setKey(final String key) {
        this.key = key;
    }

    /**
     * ��ȡ����ֵ
     * 
     * @param parameter ��������
     * @return String
     */
    public String getParameter(final String parameter) {
        final String s = (String) this.parameters.get(parameter);
        return (null == s) ? "" : s;
    }

    /**
     * ���ò���ֵ
     * 
     * @param parameter ��������
     * @param parameterValue ����ֵ
     */
    public void setParameter(final String parameter, final String parameterValue) {
        String v = "";
        if (null != parameterValue) {
            v = parameterValue.trim();
        }
        this.parameters.put(parameter, v);
    }

    /**
     * �������еĲ���
     * 
     * @return SortedMap
     */
    public SortedMap getAllParameters() {
        return this.parameters;
    }

    /**
     * ��ȡdebug��Ϣ
     */
    public String getDebugInfo() {
        return this.debugInfo;
    }

    /**
     * ��ȡ������������URL
     * 
     * @return String
     * @throws UnsupportedEncodingException
     */
    public String getRequestURL() throws UnsupportedEncodingException {

        this.createSign();

        final StringBuffer sb = new StringBuffer();
        final String enc = TenpayUtil.getCharacterEncoding(this.request, this.response);
        final Set es = this.parameters.entrySet();
        final Iterator it = es.iterator();
        while (it.hasNext()) {
            final Map.Entry entry = (Map.Entry) it.next();
            final String k = (String) entry.getKey();
            final String v = (String) entry.getValue();

            if (!"spbill_create_ip".equals(k)) {
                sb.append(k + "=" + URLEncoder.encode(v, enc) + "&");
            } else {
                sb.append(k + "=" + v.replace("\\.", "%2E") + "&");
            }
        }

        // ȥ�����һ��&
        final String reqPars = sb.substring(0, sb.lastIndexOf("&"));

        return this.getGateUrl() + "?" + reqPars;

    }

    public void doSend() throws UnsupportedEncodingException, IOException {
        this.response.sendRedirect(this.getRequestURL());
    }

    /**
     * ����md5ժҪ,������:����������a-z����,������ֵ�Ĳ������μ�ǩ����
     */
    protected void createSign() {
        final StringBuffer sb = new StringBuffer();
        final Set es = this.parameters.entrySet();
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
        sb.append("key=" + this.getKey());

        final String enc = TenpayUtil.getCharacterEncoding(this.request, this.response);
        final String sign = MD5Util.MD5Encode(sb.toString(), enc).toUpperCase();

        this.setParameter("sign", sign);

        // debug��Ϣ
        this.setDebugInfo(sb.toString() + " => sign:" + sign);

    }

    /**
     * ����debug��Ϣ
     */
    protected void setDebugInfo(final String debugInfo) {
        this.debugInfo = debugInfo;
    }

    protected HttpServletRequest getHttpServletRequest() {
        return this.request;
    }

    protected HttpServletResponse getHttpServletResponse() {
        return this.response;
    }

}
