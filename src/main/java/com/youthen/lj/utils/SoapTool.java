// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.lj.utils;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.util.Iterator;
import javax.xml.soap.MessageFactory;
import javax.xml.soap.MimeHeaders;
import javax.xml.soap.SOAPBody;
import javax.xml.soap.SOAPElement;
import javax.xml.soap.SOAPMessage;
import org.json.JSONArray;
import org.json.JSONObject;

public class SoapTool {

    public static void main(final String[] args) {

        final String str =
                "{\"unitNo\":12,\"roomNo\":\"1502\",\"totalArrearage\":7406,\"buildingArea\":185.63,\"roomCode\":\"MLY-12-1502\",\"checkInDate\":\"2009-08-29\",\"unitprice\":\"529.0\",\"CusName\":\"任少如\",\"roomName\":\"美丽苑12号1502室\",\"CusCode\":\"CU00029\",\"buildingNo\":\"6\",\"CusMobilePhone\":\"13311725992\",\"CusTypeName\":\"物业业主\",\"internalArea\":null,\"lastPeriod\":\"201412\",\"CusIsVipName\":\"否\",\"CusBirthday\":null,\"CusEmail\":null}";

        final JSONObject userInfo = new JSONObject(str);
        System.out.println("#### " + userInfo.getString("lastPeriod"));

        final String mobile = "18217345668";
        final String interfaceUrl = "http://51moft.xicp.net:20082/ljwy45/PropertyPortService.asmx";
        final String soapAction = "http://tempuri.org/getUserInfo";
        final String xmlFileName = "a.xml";
        final String method = "getUserHouseInfo";
        final SoapTool tool = new SoapTool();
        try {
            final String resultXml = tool.sendSms(interfaceUrl, soapAction, mobile, xmlFileName, method);
            final SOAPMessage msg = tool.formatSoapString(resultXml);
            final SOAPBody body = msg.getSOAPBody();
            final Iterator<SOAPElement> iterator = body.getChildElements();
            while (iterator.hasNext()) {
                final SOAPElement element = iterator.next();
                System.out.println(element.getNodeName() + " " + element.getNodeValue());

                if ((method + "Response").equals(element.getNodeName())) {
                    final Iterator<SOAPElement> it = element.getChildElements();
                    System.out.println("#### Response" + element.getNodeValue());
                    SOAPElement el = null;
                    while (it.hasNext()) {
                        el = it.next();
                        if ((method + "Result").equals(el.getLocalName())) {
                            System.out.println("#### " + el.getLocalName() + "  ====  " + el.getValue());
                            final JSONObject data = new JSONObject(el.getValue());
                            System.out.println("#### data" + data.getString("data"));
                            final JSONObject user = new JSONObject(data.getString("data"));
                            final JSONArray custArry = user.getJSONArray("CustomerInfo");
                            final Object custom = custArry.get(0);
                            // final JSONObject userInfo = new JSONObject(custom.toString());
                            System.out.println("#### " + userInfo.getString("lastPeriod"));
                        }

                    }

                }
            }
        } catch (final Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * @throws Exception
     */
    public String sendSms(final String interfaceUrl, final String soapAction, final String mobile,
            final String xmlFileName, final String method) throws Exception {
        final String xml = SoapTool.class.getClassLoader().getResource(xmlFileName).getFile();
        String xmlFile = replace(xml, "{0}", mobile).getPath();
        xmlFile = replace(xml, "{1}", mobile).getPath();
        xmlFile = replace(xml, "{2}", mobile).getPath();
        xmlFile = replace(xml, "{3}", mobile).getPath();
        xmlFile = replace(xml, "{4}", mobile).getPath();
        xmlFile = replace(xml, "{5}", mobile).getPath();
        final URL url = new URL(interfaceUrl);
        final HttpURLConnection httpConn = (HttpURLConnection) url.openConnection();
        final File fileToSend = new File(xmlFile);
        final byte[] buf = new byte[(int) fileToSend.length()];
        new FileInputStream(xmlFile).read(buf);
        httpConn.setRequestProperty("Content-Length", String.valueOf(buf.length));
        httpConn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
        httpConn.setRequestProperty("soapActionString", soapAction);
        httpConn.setRequestMethod("POST");
        httpConn.setDoOutput(true);
        httpConn.setDoInput(true);
        final OutputStream out = httpConn.getOutputStream();
        out.write(buf);
        out.close();

        final byte[] datas = readInputStream(httpConn.getInputStream());
        final String resultXml = new String(datas);
        System.out.println(resultXml);

        return resultXml;
    }

    /**
     * 文件内容替换
     * 
     * @param inFileName 源文件
     * @param from
     * @param to
     * @return 返回替换后文件
     * @throws IOException
     * @throws UnsupportedEncodingException
     */
    public static File replace(final String inFileName, final String from, final String to)
            throws IOException, UnsupportedEncodingException {
        final File inFile = new File(inFileName);
        final BufferedReader in = new BufferedReader(new InputStreamReader(
                new FileInputStream(inFile), "utf-8"));
        final File outFile = new File(inFile + ".tmp");
        final PrintWriter out = new PrintWriter(new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(outFile), "utf-8")));
        String reading;
        while ((reading = in.readLine()) != null) {
            out.println(reading.replaceAll(from, to));
        }
        out.close();
        in.close();
        inFile.delete();// ;删除源文件
        inFile.renameTo(inFile);// 对临时文件重命名
        return outFile;
    }

    /**
     * 从输入流中读取数据
     * 
     * @param inStream
     * @return
     * @throws Exception
     */
    public static byte[] readInputStream(final InputStream inStream) throws Exception {
        final ByteArrayOutputStream outStream = new ByteArrayOutputStream();
        final byte[] buffer = new byte[1024];
        int len = 0;
        while ((len = inStream.read(buffer)) != -1) {
            outStream.write(buffer, 0, len);
        }
        final byte[] data = outStream.toByteArray();// 网页的二进制数据
        outStream.close();
        inStream.close();
        return data;
    }

    /**
     * 把soap字符串格式化为SOAPMessage
     * 
     * @param soapString
     * @return
     */
    private static SOAPMessage formatSoapString(final String soapString) {
        MessageFactory msgFactory;
        try {
            msgFactory = MessageFactory.newInstance();
            final SOAPMessage reqMsg = msgFactory.createMessage(new MimeHeaders(),
                    new ByteArrayInputStream(soapString.getBytes("UTF-8")));
            reqMsg.saveChanges();
            return reqMsg;
        } catch (final Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
