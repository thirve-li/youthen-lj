package com.tenpay;

import java.util.Iterator;
import java.util.Map;
import java.util.Set;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

public class ClientRequestHandler extends PrepayIdRequestHandler {

    public ClientRequestHandler(final HttpServletRequest request,
            final HttpServletResponse response) {
        super(request, response);
        // TODO Auto-generated constructor stub
    }

    public String getXmlBody() {
        final StringBuffer sb = new StringBuffer();
        final Set es = super.getAllParameters().entrySet();
        final Iterator it = es.iterator();
        while (it.hasNext()) {
            final Map.Entry entry = (Map.Entry) it.next();
            final String k = (String) entry.getKey();
            final String v = (String) entry.getValue();
            if (!"appkey".equals(k)) {
                sb.append("<" + k + ">" + v + "<" + k + ">" + "\r\n");
            }
        }
        return sb.toString();
    }
}
