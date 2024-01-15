package com.nashtechglobal.service.impl;

import ch.qos.logback.classic.Level;
import ch.qos.logback.classic.Logger;
import ch.qos.logback.classic.spi.ILoggingEvent;
import ch.qos.logback.core.read.ListAppender;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.slf4j.LoggerFactory;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class CoreLoggerImplTest {

    private static final String LOG_STRING = "Test log string";

    private static final String LOG_STRING_WITH_ARG = "Test log string with arg {} and {}";
    private static final String LOG_STRING_FORMATTED_MSG = "Test log string with arg ARG1 and ARG2";
    private static final String ARG1 = "ARG1";
    private static final String ARG2 = "ARG2";
    private Logger logger;
    private ListAppender<ILoggingEvent> listAppender;
    private CoreLoggerImpl coreLogger;
    private List<ILoggingEvent> loggingEvents;
    @BeforeEach
    void setup() {
        logger = (Logger) LoggerFactory.getLogger(CoreLoggerImpl.class);
        listAppender = new ListAppender<>();
        listAppender.start();
        logger.addAppender(listAppender);
        coreLogger = new CoreLoggerImpl();
        loggingEvents = listAppender.list;
    }

    @Test
    void info() {
        coreLogger.info(LOG_STRING);
        assertEquals(LOG_STRING, loggingEvents.get(0).getMessage());
        assertEquals(Level.INFO, loggingEvents.get(0).getLevel());
    }
    @Test
    void infoWithArgument() {
        coreLogger.info(LOG_STRING_WITH_ARG,ARG1,ARG2);
        assertEquals(LOG_STRING_FORMATTED_MSG, loggingEvents.get(0).getFormattedMessage());
        assertEquals(Level.INFO, loggingEvents.get(0).getLevel());
    }
    @Test
    void debug() {
        coreLogger.debug(LOG_STRING);
        assertEquals(LOG_STRING, loggingEvents.get(0).getMessage());
        assertEquals(Level.DEBUG, loggingEvents.get(0).getLevel());
    }
    @Test
    void debugWithArgument() {
        coreLogger.debug(LOG_STRING_WITH_ARG,ARG1,ARG2);
        assertEquals(LOG_STRING_FORMATTED_MSG, loggingEvents.get(0).getFormattedMessage());
        assertEquals(Level.DEBUG, loggingEvents.get(0).getLevel());
    }
    @Test
    void error() {
        coreLogger.error(LOG_STRING);
        assertEquals(LOG_STRING, loggingEvents.get(0).getMessage());
        assertEquals(Level.ERROR, loggingEvents.get(0).getLevel());
    }
    @Test
    void errorWithArgument() {
        coreLogger.error(LOG_STRING_WITH_ARG,ARG1,ARG2);
        assertEquals(LOG_STRING_FORMATTED_MSG, loggingEvents.get(0).getFormattedMessage());
        assertEquals(Level.ERROR, loggingEvents.get(0).getLevel());
    }
    @Test
    void warn() {
        coreLogger.warn(LOG_STRING);
        assertEquals(LOG_STRING, loggingEvents.get(0).getMessage());
        assertEquals(Level.WARN, loggingEvents.get(0).getLevel());
    }
    @Test
    void warnWithArgument() {
        coreLogger.warn(LOG_STRING_WITH_ARG,ARG1,ARG2);
        assertEquals(LOG_STRING_FORMATTED_MSG, loggingEvents.get(0).getFormattedMessage());
        assertEquals(Level.WARN, loggingEvents.get(0).getLevel());
    }

    @Test
    void trace() {
        logger.setLevel(Level.TRACE);
        coreLogger.trace(LOG_STRING);
        assertEquals(LOG_STRING, loggingEvents.get(0).getMessage());
        assertEquals(Level.TRACE, loggingEvents.get(0).getLevel());
    }

    @Test
    void traceWithArguments() {
        logger.setLevel(Level.TRACE);
        coreLogger.trace(LOG_STRING_WITH_ARG, ARG1, ARG2);
        assertEquals(LOG_STRING_FORMATTED_MSG, loggingEvents.get(0).getFormattedMessage());
        assertEquals(Level.TRACE, loggingEvents.get(0).getLevel());
    }

    @Test
    void isDebugEnabled() {
        assertTrue(coreLogger.isDebugEnabled());
    }

    @Test
    void isTraceEnabled() {
        assertFalse(coreLogger.isTraceEnabled());
    }
}
