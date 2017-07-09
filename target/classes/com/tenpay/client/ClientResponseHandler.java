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
 * 后台应答类<br/>
 * ========================================================================<br/>
 * api说明：<br/>
 * getKey()/setKey(),获取/设置密钥<br/>
 * getContent() / setContent(), 获取/设置原始内容<br/>
 * getParameter()/setParameter(),获取/设置参数值<br/>
 * getAllParameters(),获取所有参数<br/>
 * isTenpaySign(),是否财付通签名,true:是 false:否<br/>
 * getDebugInfo(),获取debug信息<br/>
 * ========================================================================<br/>
 */
public class ClientResponseHandler {

    /** 应答原始内容 */
    private String content;

    /** 应答的参数 */
    private final SortedMap parameters;

    /** debug信息 */
    private String debugInfo;

    /** 密钥 */
    private String key;

    /** 字符集 */
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
     * 获取参数值
     * 
     * @param parameter 参数名称
     * @return String
     */
    public String getParameter(final String parameter) {
        final String s = (String) this.parameters.get(parameter);
        return (null == s) ? "" : s;
    }

    /**
     * 设置参数值
     * 
     * @param parameter 参数名称
     * @param parameterValue 参数值
     */
    public void setParameter(final String parameter, final String parameterValue) {
        String v = "";
        if (null != parameterValue) {
            v = parameterValue.trim();
        }
        this.parameters.put(parameter, v);
    }

    /**
     * 返回所有的参数
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
     * 获取密钥
     */
    public String getKey() {
        return this.key;
    }

    /**
     * 设置密钥
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
     * 是否财付通签名,规则是:按参数名称a-z排序,遇到空值的参数不参加签名。
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

        // 算出摘要
        final String sign = MD5Util.MD5Encode(sb.toString(), this.charset).toLowerCase();

        final String tenpaySign = this.getParameter("sign").toLowerCase();

        // debug信息
        this.setDebugInfo(sb.toString() + " => sign:" + sign +
                " tenpaySign:" + tenpaySign);

        return tenpaySign.equals(sign);
    }

    /**
     * 是否财付通签名
     * 
     * @param signParameterArray 签名的参数数组
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

        // 算出摘要
        final String sign = MD5Util.MD5Encode(
                signPars.toString(), this.charset).toLowerCase();

        final String tenpaySign = this.getParameter("sign").toLowerCase();

        // debug信息
        this.setDebugInfo(signPars.toString() + " => sign:" + sign +
                " tenpaySign:" + tenpaySign);

        return tenpaySign.equals(sign);
    }

    protected void setDebugInfo(final String debugInfo) {
        this.debugInfo = debugInfo;
    }

    /**
     * 解析XML内容
     */
    protected void doParse() throws JDOMException, IOException {

        System.out.println("=====================>doParse:");
        final String xmlContent = this.getContent();

        // 解析xml,得到map
        final Map m = XMLUtil.doXMLParse(xmlContent);

        System.out.println("=====================>xmlContent : " + m);

        // 设置参数
        final Iterator it = m.keySet().iterator();
        while (it.hasNext()) {
            final String k = (String) it.next();
            final String v = (String) m.get(k);
            this.setParameter(k, v);
        }

    }

    /**
     * 解析XML内容
     */
    protected void ljDoParse() throws JDOMException, IOException {

        System.out.println("=====================>ljDoParse:");
        final String xmlContent = this.getContent();

        // 解析xml,得到map
        final Map m = XMLUtil.LjdoXMLParse(xmlContent);

        System.out.println("=====================>xmlContent : " + m);

        // 设置参数
        final Iterator it = m.keySet().iterator();
        while (it.hasNext()) {
            final String k = (String) it.next();
            final String v = (String) m.get(k);
            this.setParameter(k, v);
        }

    }

}
