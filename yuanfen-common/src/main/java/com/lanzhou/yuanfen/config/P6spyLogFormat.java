package com.lanzhou.yuanfen.config;

import com.p6spy.engine.spy.appender.MessageFormattingStrategy;

import java.util.Date;

import java.text.SimpleDateFormat;


/**
 * 配置sql 打印日志, 记住修改url链接哦
 * @author Administrator
 */
public class P6spyLogFormat implements MessageFormattingStrategy {

    private SimpleDateFormat format = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss:SSS");

    /**
     * Formats a log message for the logging module
     *
     * @param connectionId the id of the connection
     * @param now          the current ime expressing in milliseconds
     * @param elapsed      the time in milliseconds that the operation took to complete
     * @param category     the category of the operation
     * @param prepared     the SQL statement with all bind variables replaced with actual values
     * @param sql          the sql statement executed
     * @param url          the database url where the sql statement executed
     * @return the formatted log message
     */
    @Override
    public String formatMessage(int connectionId, String now, long elapsed, String category, String prepared, String sql, String url) {
        return !"".equals(sql.trim()) ? this.format.format(new Date()) + " | Elapsed time " + elapsed + "ms | " + category + " | Connection ID" + connectionId + "\n " + sql + ";" : "";
    }

}
