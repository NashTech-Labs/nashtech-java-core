package com.nashtechglobal.service.impl;

import com.nashtechglobal.service.CoreLogger;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@Component
public class CoreLoggerImpl implements CoreLogger {
    /**
     * @param message
     */
    @Override
    public void info(final String message) {
        log.info(message);
    }

    /**
     * It provides the Info Log.
     *
     * @param message
     * @param args
     */
    @Override
    public void info(final String message, final Object... args) {
        log.info(message, args);
    }

    /**
     * @param message
     */
    @Override
    public void debug(final String message) {
        log.debug(message);
    }

    /**
     * It provide the debug Log.
     *
     * @param message
     * @param args
     */
    @Override
    public void debug(final String message, final Object... args) {
        log.debug(message, args);
    }

    /**
     * @param message
     */
    @Override
    public void error(final String message) {
        log.error(message);
    }

    /**
     * It provide the ERROR Log.
     *
     * @param message
     * @param args
     */
    @Override
    public void error(final String message, final Object... args) {
        log.error(message, args);
    }

    /**
     * @param message
     */
    @Override
    public void trace(final String message) {
        log.trace(message);
    }

    /**
     * @param message
     * @param args
     */
    @Override
    public void trace(final String message, final Object... args) {
        log.trace(message, args);
    }

    /**
     * @param message
     */
    @Override
    public void warn(final String message) {
        log.warn(message);
    }

    /**
     * @param message
     * @param args
     */
    @Override
    public void warn(final String message, final Object... args) {
        log.warn(message, args);
    }

    /**
     * This method return of log level is DEBUG or higher.
     * @return
     */
    @Override
    public boolean isDebugEnabled() {
        return log.isDebugEnabled();
    }

    /**
     * This method return of log level is TRACE or higher.
     * @return
     */
    @Override
    public boolean isTraceEnabled() {
        return log.isTraceEnabled();
    }
}
