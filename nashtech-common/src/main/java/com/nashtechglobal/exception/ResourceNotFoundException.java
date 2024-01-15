package com.nashtechglobal.exception;

public class ResourceNotFoundException extends RuntimeException {
    /**
     * Constructs a ResourceNotFoundException with
     * the specified detail message.
     * @param ex the detail message that provides
     * information about the resource that was not found.
     */
    public ResourceNotFoundException(final String ex) {
        super(ex);
    }
}
