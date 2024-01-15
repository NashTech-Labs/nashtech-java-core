package com.nashtech.observation.handler;


import io.micrometer.observation.Observation;
import io.micrometer.observation.ObservationHandler;
import io.micrometer.observation.tck.AnyContextObservationHandlerCompatibilityKit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest(classes = TraceObservationHandler.class)
class ObservationHandlerTest extends AnyContextObservationHandlerCompatibilityKit {

    TraceObservationHandler traceObservationHandler = new TraceObservationHandler();

    @Override
    public ObservationHandler<Observation.Context> handler() {
        return traceObservationHandler;
    }
}
