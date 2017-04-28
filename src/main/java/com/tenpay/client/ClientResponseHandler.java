package com.tenpay.client;

import java.io.IOException;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import java.util.SortedMap;
import java.util.TreeMap;
import org.jdom.JDOMException;
import com.tenpay.util.MD5Util;
import com.tenpay.util.XMLUtil;

/**
 * ��̨Ӧ����<br/>
 * ========================================================================<br/>
 * api˵����<br/>
 * getKey()/setKey(),��ȡ/������Կ<br/>
 * getContent() / setContent(), ��ȡ/����ԭʼ����<br/>
 * getParameter()/setParameter(),��ȡ/���ò���ֵ<br/>
 * getAllParameters(),��ȡ���в���<br/>
 * isTenpaySign(),�Ƿ�Ƹ�ͨǩ��,true:�� false:��<br/>
 * getDebugInfo(),��ȡdebug��Ϣ<br/>
 * ========================================================================<br/>
 */
public class ClientResponseHandler {

    /** Ӧ��ԭʼ���� */
    private String content;

    /** Ӧ��Ĳ��� */
    private final SortedMap parameters;

    /** debug��Ϣ */
    private String debugInfo;

    /** ��Կ */
    private String key;

    /** �ַ��� */
    private String charset;

    public ClientResponseHandler() {
        this.content = "";
        this.parameters = new TreeMap();
        this.debugInfo = "";
        this.key = "";
        this.charset = "GBK";
    }

    public String getContent() {
        return this.content;
    }

    public void setContent(final String content) throws Exception {
        this.content = content;

        this.doParse();
    }

    public void setLjContent(final String content) throws Exception {
        this.content = content;

        this.ljDoParse();
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

    public String getDebugInfo() {
        return this.debugInfo;
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

    public String getCharset() {
        return this.charset;
    }

    public void setCharset(final String charset) {
        this.charset = charset;
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
        final String sign = MD5Util.MD5Encode(sb.toString(), this.charset).toLowerCase();

        final String tenpaySign = this.getParameter("sign").toLowerCase();

        // debug��Ϣ
        this.setDebugInfo(sb.toString() + " => sign:" + sign +
                " tenpaySign:" + tenpaySign);

        return tenpaySign.equals(sign);
    }

    /**
     * �Ƿ�Ƹ�ͨǩ��
     * 
     * @param signParameterArray ǩ���Ĳ�������
     * @return boolean
     */
    protected boolean isTenpaySign(final String signParameterArray[]) {

        final StringBuffer signPars = new StringBuffer();
        for (int index = 0; index < signParameterArray.length; index++) {
            final String k = signParameterArray[index];
            final String v = this.getParameter(k);
            if (null != v && !"".equals(v)) {
                signPars.append(k + "=" + v + "&");
            }
        }

        signPars.append("key=" + this.getKey());

        // ���ժҪ
        final String sign = MD5Util.MD5Encode(
                signPars.toString(), this.charset).toLowerCase();

        final String tenpaySign = this.getParameter("sign").toLowerCase();

        // debug��Ϣ
        this.setDebugInfo(signPars.toString() + " => sign:" + sign +
                " tenpaySign:" + tenpaySign);

        return tenpaySign.equals(sign);
    }

    protected void setDebugInfo(final String debugInfo) {
        this.debugInfo = debugInfo;
    }

    /**
     * ����XML����
     */
    protected void doParse() throws JDOMException, IOException {

        System.out.println("=====================>doParse:");
        final String xmlContent = this.getContent();

        // ����xml,�õ�map
        final Map m = XMLUtil.doXMLParse(xmlContent);

        System.out.println("=====================>xmlContent : " + m);

        // ���ò���
        final Iterator it = m.keySet().iterator();
        while (it.hasNext()) {
            final String k = (String) it.next();
            final String v = (String) m.get(k);
            this.setParameter(k, v);
        }

    }

    /**
     * ����XML����
     */
    protected void ljDoParse() throws JDOMException, IOException {

        System.out.println("=====================>ljDoParse:");
        final String xmlContent = this.getContent();

        // ����xml,�õ�map
        final Map m = XMLUtil.LjdoXMLParse(xmlContent);

        System.out.println("=====================>xmlContent : " + m);

        // ���ò���
        final Iterator it = m.keySet().iterator();
        while (it.hasNext()) {
            final String k = (String) it.next();
            final String v = (String) m.get(k);
            this.setParameter(k, v);
        }

    }

}
