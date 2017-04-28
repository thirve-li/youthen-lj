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
 * 应答处理类
 * 应答处理类继承此类，重写isTenpaySign方法即可。
 * 
 * @author miklchen
 */
public class ResponseHandler {

    /** 密钥 */
    private String key;

    /** 应答的参数 */
    private SortedMap parameters;

    /** debug信息 */
    private String debugInfo;

    private final HttpServletRequest request;

    private final HttpServletResponse response;

    private String uriEncoding;

    /**
     * 构造函数
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

    public void setParameter(final SortedMap map) {
        this.parameters = map;
    }

    /**
     * 返回所有的参数
     * 
     * @return SortedMap
     */
    public SortedMap getAllParameters() {
        return this.parameters;
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
        final String enc = TenpayUtil.getCharacterEncoding(this.request, this.response);
        final String sign = MD5Util.MD5Encode(sb.toString(), enc).toLowerCase();

        final String tenpaySign = this.getParameter("sign").toLowerCase();

        // debug信息
        this.setDebugInfo(sb.toString() + " => sign:" + sign +
                " tenpaySign:" + tenpaySign);

        return tenpaySign.equals(sign);
    }

    /**
     * 返回处理结果给财付通服务器。
     * 
     * @param msg: Success or fail。
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
     * 获取uri编码
     * 
     * @return String
     */
    public String getUriEncoding() {
        return this.uriEncoding;
    }

    /**
     * 设置uri编码
     * 
     * @param uriEncoding
     * @throws UnsupportedEncodingException
     */
    public void setUriEncoding(final String uriEncoding)
            throws UnsupportedEncodingException {
        if (!"".equals(uriEncoding.trim())) {
            this.uriEncoding = uriEncoding;

            // 编码转换
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
     * 获取debug信息
     */
    public String getDebugInfo() {
        return this.debugInfo;
    }

    /**
     * 设置debug信息
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
