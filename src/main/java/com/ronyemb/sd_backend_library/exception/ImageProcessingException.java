package com.ronyemb.sd_backend_library.exception;

public class ImageProcessingException extends RuntimeException {

    // Constructor con mensaje
    public ImageProcessingException(String message) {
        super(message);
    }

    // Constructor con mensaje y causa
    public ImageProcessingException(String message, Throwable cause) {
        super(message, cause);
    }

    // Constructor con causa
    public ImageProcessingException(Throwable cause) {
        super(cause);
    }
}
