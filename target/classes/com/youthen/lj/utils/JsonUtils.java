package com.youthen.lj.utils;

import java.io.StringWriter;
import java.io.Writer;
import net.sf.json.JSONObject;
import org.apache.log4j.Logger;
import org.codehaus.jackson.map.ObjectMapper;
import com.youthen.lj.app.bean.Result;

public class JsonUtils {

    public static void main(final String[] args) {

    }

    private static ObjectMapper mapper = new ObjectMapper();
    static Logger logger = Logger.getLogger(JsonUtils.class.getName());

    public static String resultToString(final Result object) {
        try {
            final Writer strWriter = new StringWriter();
            mapper.writeValue(strWriter, object);
            final String dataJSON = strWriter.toString();
            return dataJSON;
        } catch (final Exception exception) {
            logger.error(exception.getMessage());
            return "";
        }
    }

    public static String objectToString(final Object object) {
        try {
            final Writer strWriter = new StringWriter();
            mapper.writeValue(strWriter, object);
            final String dataJSON = strWriter.toString();
            return dataJSON;
        } catch (final Exception exception) {
            logger.error(exception.getMessage());
            return "";
        }
    }

    public static Result stringToResult(final String string) {
        try {
            final Result result = mapper.readValue(string, Result.class);
            return result;
        } catch (final Exception exception) {
            logger.error(exception.getMessage());
            return null;
        }
    }

    public static Result stringToResult(final String string, final Class t) {
        try {
            final Result result = mapper.readValue(string, t);
            return result;
        } catch (final Exception exception) {
            logger.error(exception.getMessage());
            return null;
        }
    }

    public static <T> Object stringToObject(final String string, final Class t) {
        try {
            final Object result = mapper.readValue(string, t);
            return result;
        } catch (final Exception exception) {
            logger.error(exception.getMessage());
            return null;
        }
    }

    public static JSONObject stringToObject(final String string) {
        try {

            final JSONObject jsonObject = JSONObject.fromObject(string);
            return jsonObject;
        } catch (final Exception exception) {
            logger.error(exception.getMessage());
            return null;
        }
    }

}
