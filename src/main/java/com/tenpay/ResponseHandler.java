package com.tenpay;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
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
 * Ӧ������
 * Ӧ������̳д��࣬��дisTenpaySign�������ɡ�
 * 
 * @author miklchen
 */
public class ResponseHandler {

    /** ��Կ */
    private String key;

    /** Ӧ��Ĳ��� */
    private SortedMap parameters;

    /** debug��Ϣ */
    private String debugInfo;

    private final HttpServletRequest request;

    private final HttpServletResponse response;

    private String uriEncoding;

    /**
     * ���캯��
     * 
     * @param request
     * @param response
     */
    public ResponseHandler(final HttpServletRequest request,
            final HttpServletResponse response) {
        this.request = request;
        this.response = response;

        this.key = "";
        this.parameters = new TreeMap();
        this.debugInfo = "";

        this.uriEncoding = "";

        final Map m = this.request.getParameterMap();
        final Iterator it = m.keySet().iterator();
        while (it.hasNext()) {
            final String k = (String) it.next();
            final String v = ((String[]) m.get(k))[0];

            System.out.println(k + "===============" + v);
            this.setParameter(k, v);
        }

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

    public void setParameter(final SortedMap map) {
        this.parameters = map;
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
     * �Ƿ�Ƹ�ͨǩ��,������:����������a-z����,������ֵ�Ĳ������μ�ǩ����
     * 
     * @return boolean
     */
    public boolean isTenpaySign() {
        final StringBuffer sb = new StringBuffer();
        final Set es = this.parameters.entrySet();
        final Iterator it = es.iterator();
        while (it.hasNext()) {
            final Map.Entry entry = (Map.Entry) it.next();
            final String k = (String) entry.getKey();
            final String v = (String) entry.getValue();
            if (!"sign".equals(k) && null != v && !"".equals(v)) {
                sb.append(k + "=" + v + "&");
            }
        }

        sb.append("key=" + this.getKey());

        // ���ժҪ
        final String enc = TenpayUtil.getCharacterEncoding(this.request, this.response);
        final String sign = MD5Util.MD5Encode(sb.toString(), enc).toLowerCase();

        final String tenpaySign = this.getParameter("sign").toLowerCase();

        // debug��Ϣ
        this.setDebugInfo(sb.toString() + " => sign:" + sign +
                " tenpaySign:" + tenpaySign);

        return tenpaySign.equals(sign);
    }

    /**
     * ���ش��������Ƹ�ͨ��������
     * 
     * @param msg: Success or fail��
     * @throws IOException
     */
    public void sendToCFT(final String msg) throws IOException {
        final String strHtml = msg;
        final PrintWriter out = this.getHttpServletResponse().getWriter();
        out.println(strHtml);
        out.flush();
        out.close();

    }

    /**
     * ��ȡuri����
     * 
     * @return String
     */
    public String getUriEncoding() {
        return this.uriEncoding;
    }

    /**
     * ����uri����
     * 
     * @param uriEncoding
     * @throws UnsupportedEncodingException
     */
    public void setUriEncoding(final String uriEncoding)
            throws UnsupportedEncodingException {
        if (!"".equals(uriEncoding.trim())) {
            this.uriEncoding = uriEncoding;

            // ����ת��
            final String enc = TenpayUtil.getCharacterEncoding(this.request, this.response);
            final Iterator it = this.parameters.keySet().iterator();
            while (it.hasNext()) {
                final String k = (String) it.next();
                String v = this.getParameter(k);
                v = new String(v.getBytes(uriEncoding.trim()), enc);
                this.setParameter(k, v);
            }
        }
    }

    /**
     * ��ȡdebug��Ϣ
     */
    public String getDebugInfo() {
        return this.debugInfo;
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
