package com.nashtechglobal.reactive.service;

/**
 * This interface defines a callback that can be used for handling the
 * result of a reactive request.
 */
public interface ReactiveRequestCallback {

    /**
     * Called when the reactive request succeeds.
     *
     * @param statusCode The HTTP status code returned by the request.
     */
    void onSuccess(Integer statusCode);

    /**
     * Called when an error occurs while processing the reactive request.
     *
     * @param throwable The {@link Throwable} object that represents
     *                  the error that occurred.
     */
    void onError(Throwable throwable);
}
