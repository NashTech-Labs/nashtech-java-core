package com.nashtechglobal.service;

public interface CoreLogger {
    /**
     *
     * @param message
     */
    void info(String message);

    /**
     *
     * @param message
     * @param args
     */
    void info(String message, Object... args);

    /**
     *
     * @param message
     */
    void debug(String message);

    /**
     *
     * @param message
     * @param args
     */
    void debug(String message, Object... args);

    /**
     *
     * @param message
     */
    void error(String message);

    /**
     *
     * @param message
     * @param args
     */
    void error(String message, Object... args);

    /**
     *
     * @param message
     */
    void trace(String message);

    /**
     *
     * @param message
     * @param args
     */
    void trace(String message, Object... args);
    /**
     *
     * @param message
     */
    void warn(String message);

    /**
     *
     * @param message
     * @param args
     */
    void warn(String message, Object... args);

    /**
     *
     * @return boolean
     */
    boolean isDebugEnabled();

    /**
     *
     * @return boolean
     */
    boolean isTraceEnabled();
}
