package com.tenpay;

import org.slf4j.Logger;

/**
 * User: rizenguo
 * Date: 2014/11/12
 * Time: 14:32
 */
public class Log {

    public static final String LOG_TYPE_TRACE = "logTypeTrace";
    public static final String LOG_TYPE_DEBUG = "logTypeDebug";
    public static final String LOG_TYPE_INFO = "logTypeInfo";
    public static final String LOG_TYPE_WARN = "logTypeWarn";
    public static final String LOG_TYPE_ERROR = "logTypeError";

    // 打印日志
    private final Logger logger;

    public Log(final Logger log) {
        this.logger = log;
    }

    public void t(final String s) {
        this.logger.trace(s);
    }

    public void d(final String s) {
        this.logger.debug(s);
    }

    public void i(final String s) {
        this.logger.info(s);
    }

    public void w(final String s) {
        this.logger.warn(s);
    }

    public void e(final String s) {
        this.logger.error(s);
    }

    public void log(final String type, final String s) {
        if (type.equals(Log.LOG_TYPE_TRACE)) {
            t(s);
        } else if (type.equals(Log.LOG_TYPE_DEBUG)) {
            d(s);
        } else if (type.equals(Log.LOG_TYPE_INFO)) {
            i(s);
        } else if (type.equals(Log.LOG_TYPE_WARN)) {
            w(s);
        } else if (type.equals(Log.LOG_TYPE_ERROR)) {
            e(s);
        }
    }

}
