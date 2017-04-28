// ============================================================
// Copyright(c) youthen Incorporated All Right Reserved.
// File: $Id$
// ============================================================

package com.youthen.lj.utils;

import java.io.BufferedReader;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.OutputStream;
import java.io.OutputStreamWriter;
import java.net.URL;
import java.net.URLConnection;

/**
 * 。
 * 
 * @author Dbl
 * @author Modifier By $Author: $
 * @version $Revision: $<br>
 *          $Date: $
 */
public class LjHttpSoap {

    public String getSoapInputStream(final String methodName, final String param[]) {
        try {
            final URL url = new URL("http://51moft.xicp.net:20082/ljwy45/PropertyPortService.asmx");
            String soap = "";
            if ("getHouseInfo".equals(methodName) || "getParkInfo".equals(methodName)) {
                soap =
                        "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
                                + "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "
                                + "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
                                + "<soap:Body>"
                                + "<" + methodName + " xmlns=\"http://tempuri.org/\">"
                                + "<appKey>201512300001</appKey>"
                                + "<appPassword>ZXWFVB_GHL-123</appPassword>"
                                + "<queryType>3</queryType>"
                                + "<queryStr>" + param[0] + "</queryStr>"
                                + "</" + methodName + ">"
                                + "</soap:Body>"
                                + "</soap:Envelope>";
            } else if ("procParkingJiaoFei".equals(methodName)) {
                soap =
                        "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
                                + "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "
                                + "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
                                + "<soap:Body>"
                                + "<" + methodName + " xmlns=\"http://tempuri.org/\">"
                                + "<appKey>201512300001</appKey>"
                                + "<appPassword>ZXWFVB_GHL-123</appPassword>"
                                + "<parkingNo>" + param[0] + "</parkingNo>"
                                + "<endQibie>" + param[1] + "</endQibie>"
                                + "</" + methodName + ">"
                                + "</soap:Body>"
                                + "</soap:Envelope>";
            } else if ("procWYGLJiaoFei".equals(methodName)) {
                soap =
                        "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
                                + "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "
                                + "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
                                + "<soap:Body>"
                                + "<" + methodName + " xmlns=\"http://tempuri.org/\">"
                                + "<appKey>201512300001</appKey>"
                                + "<appPassword>ZXWFVB_GHL-123</appPassword>"
                                + "<roomCode>" + param[0] + "</roomCode>"
                                + "<endQibie>" + param[1] + "</endQibie>"
                                + "</" + methodName + ">"
                                + "</soap:Body>"
                                + "</soap:Envelope>";
            } else if ("getUserPayHistory".equals(methodName)) {
                soap =
                        "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
                                + "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "
                                + "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
                                + "<soap:Body>"
                                + "<" + methodName + " xmlns=\"http://tempuri.org/\">"
                                + "<appKey>201512300001</appKey>"
                                + "<appPassword>ZXWFVB_GHL-123</appPassword>"
                                + "<mobileNum>" + param[0] + "</mobileNum>"
                                + "<roomCodes>" + param[1] + "</roomCodes>"
                                + "<parkNos>" + param[2] + "</parkNos>"
                                + "</" + methodName + ">"
                                + "</soap:Body>"
                                + "</soap:Envelope>";
            }
            else {
                soap =
                        "<?xml version=\"1.0\" encoding=\"utf-8\"?>"
                                + "<soap:Envelope xmlns:xsi=\"http://www.w3.org/2001/XMLSchema-instance\" "
                                + "xmlns:xsd=\"http://www.w3.org/2001/XMLSchema\" xmlns:soap=\"http://schemas.xmlsoap.org/soap/envelope/\">"
                                + "<soap:Body>"
                                + "<" + methodName + " xmlns=\"http://tempuri.org/\">"
                                + "<appKey>201512300001</appKey>"
                                + "<appPassword>ZXWFVB_GHL-123</appPassword>"
                                + "<mobileNum>" + param[0] + "</mobileNum>"
                                + "</" + methodName + ">"
                                + "</soap:Body>"
                                + "</soap:Envelope>";
            }

            final URLConnection conn = url.openConnection();
            conn.setUseCaches(false);
            conn.setDoInput(true);
            conn.setDoOutput(true);
            conn.setRequestProperty("Content-Length", Integer.toString(soap.length()));
            conn.setRequestProperty("Content-Type", "text/xml; charset=utf-8");
            conn.setRequestProperty("SOAPAction", "http://tempuri.org/" + methodName + "");
            final OutputStream os = conn.getOutputStream();
            final OutputStreamWriter osw = new OutputStreamWriter(os, "utf-8");
            osw.write(soap);
            osw.flush();
            osw.close();
            final StringBuilder sTotalString = new StringBuilder();
            String sCurrentLine = "";
            final InputStream is = conn.getInputStream();
            final BufferedReader l_reader = new BufferedReader(new InputStreamReader(is));
            while ((sCurrentLine = l_reader.readLine()) != null) {
                sTotalString.append(sCurrentLine);
            }
            final String str = new String(sTotalString.toString().getBytes("gbk"), "utf-8");
            return str;
            // return sTotalString.toString();

        } catch (final Exception e) {
            e.printStackTrace();
        }
        return null;
    }
}
