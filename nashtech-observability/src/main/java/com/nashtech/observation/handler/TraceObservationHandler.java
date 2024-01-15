package com.nashtech.observation.handler;

import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationHandler;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class TraceObservationHandler implements
        ObservationHandler<Observation.Context> {

    private static String toString(final Observation.Context context) {
        return null == context ? "(no context)" : context.getName()
                + " (" + context.getClass().getName() + "@"
                + System.identityHashCode(context) + ")";
    }

    private static String toString(final Observation.Event event) {
        return null == event ? "(no event)" : event.getName();
    }

    /**
     *  Returns if the handler supports the context.
     * @param context
     * @return
     */
    @Override
    public boolean supportsContext(final Observation.Context context) {
        return true;
    }

    /**
     *  Logs the start of the observation.
     * @param context
     */
    @Override
    public void onStart(final Observation.Context context) {
        log.info("Starting context " + toString(context));
    }

    /**
     *  Logs the error on the observation.
     * @param context
     */
    @Override
    public void onError(final Observation.Context context) {
        log.info("Error for context " + toString(context));
    }

    /**
     *  Logs the event of the observation.
     * @param event
     * @param context
     */
    @Override
    public void onEvent(final Observation.Event event,
                        final Observation.Context context) {
        log.info("Event for context "
                + toString(context) + " [" + toString(event) + "]");
    }

    /**
     *  Logs the event when the scope is opened.
     * @param context
     */
    @Override
    public void onScopeOpened(final Observation.Context context) {
        log.info("Scope opened for context " + toString(context));

    }

    /**
     *  Logs the event when the scope is closed.
     * @param context
     */
    @Override
    public void onScopeClosed(final Observation.Context context) {
        log.info("Scope closed for context " + toString(context));
    }

    /**
     *  Logs the event when the scope is stopped.
     * @param context
     */
    @Override
    public void onStop(final Observation.Context context) {
        log.info("Stopping context " + toString(context));
    }
}
