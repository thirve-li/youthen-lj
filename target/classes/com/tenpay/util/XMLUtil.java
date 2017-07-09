package com.tenpay.util;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import org.jdom.Document;
import org.jdom.Element;
import org.jdom.JDOMException;
import org.jdom.input.SAXBuilder;

/**
 * xml������
 * 
 * @author miklchen
 */
public class XMLUtil {

    /**
     * ����xml,���ص�һ��Ԫ�ؼ�ֵ�ԡ������һ��Ԫ�����ӽڵ㣬��˽ڵ��ֵ���ӽڵ��xml���ݡ�
     * 
     * @param strxml
     * @return
     * @throws JDOMException
     * @throws IOException
     */
    public static Map doXMLParse(String strxml) throws JDOMException, IOException {

        strxml = strxml.replaceFirst("encoding=\".*\"", "encoding=\"UTF-8\"");

        if (null == strxml || "".equals(strxml)) {
            return null;
        }
        final Map m = new HashMap();

        final InputStream in = new ByteArrayInputStream(strxml.getBytes("UTF-8"));
        final SAXBuilder builder = new SAXBuilder();
        final Document doc = builder.build(in);
        final Element root = doc.getRootElement();
        final List list = root.getChildren();
        final Iterator it = list.iterator();
        while (it.hasNext()) {
            final Element e = (Element) it.next();
            final String k = e.getName();
            String v = "";
            final List children = e.getChildren();
            if (children.isEmpty()) {
                v = e.getTextNormalize();
            } else {
                v = XMLUtil.getChildrenText(children);
            }
            m.put(k, v);
        }

        // �ر���
        in.close();
        return m;
    }

    public static Map LjdoXMLParse(final String strxml) throws JDOMException, IOException {

        if (null == strxml || "".equals(strxml)) {
            return null;
        }
        final Map m = new HashMap();

        final InputStream in = new ByteArrayInputStream(strxml.getBytes("UTF-8"));
        final SAXBuilder builder = new SAXBuilder();
        final Document doc = builder.build(in);
        final Element root = doc.getRootElement();
        final List list = root.getChildren();
        final Iterator it = list.iterator();
        while (it.hasNext()) {
            final Element e = (Element) it.next();
            final String k = e.getName();
            String v = "";
            final List children = e.getChildren();
            if (children.isEmpty()) {
                v = e.getTextNormalize();
            } else {
                v = XMLUtil.getChildrenText(children);
            }
            m.put(k, v);
        }

        // �ر���
        in.close();
        return m;
    }

    /**
     * ��ȡ�ӽ���xml
     * 
     * @param children
     * @return String
     */
    public static String getChildrenText(final List children) {
        final StringBuffer sb = new StringBuffer();
        if (!children.isEmpty()) {
            final Iterator it = children.iterator();
            while (it.hasNext()) {
                final Element e = (Element) it.next();
                final String name = e.getName();
                final String value = e.getTextNormalize();
                final List list = e.getChildren();
                sb.append("<" + name + ">");
                if (!list.isEmpty()) {
                    sb.append(XMLUtil.getChildrenText(list));
                }
                sb.append(value);
                sb.append("</" + name + ">");
            }
        }

        return sb.toString();
    }

    /**
     * ��ȡxml�����ַ���
     * 
     * @param strxml
     * @return
     * @throws IOException
     * @throws JDOMException
     */
    public static String getXMLEncoding(final String strxml) throws JDOMException, IOException {
        final InputStream in = HttpClientUtil.String2Inputstream(strxml);
        final SAXBuilder builder = new SAXBuilder();
        final Document doc = builder.build(in);
        in.close();
        return (String) doc.getProperty("encoding");
    }

}
