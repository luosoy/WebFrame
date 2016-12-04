/**
 *
 */
package com.luosoy.frame.exception;

@SuppressWarnings("serial")
public class BusinessException extends BaseException {

    /**
     * The Constant BASECODE_EXCEPTION.
     */
    public static final String BASECODE_EXCEPTION = "000202";

    /**
     * The Constant JSON_EXCEPTION.
     */
    public static final String JSON_EXCEPTION = "000203";

    /**
     * The Constant REQUEST_PARAM_EXCEPTION.
     */
    public static final String REQUEST_PARAM_EXCEPTION = "000204";

    /**
     * Instantiates a new business exception.
     *
     * @param message the message
     * @param cause the cause
     * @param errorCode the error code
     */
    public BusinessException(String message, Throwable cause, String errorCode) {
        super(message, cause, errorCode);
    }

    /**
     * Instantiates a new business exception.
     *
     * @param message the message
     * @param errorCode the error code
     */
    public BusinessException(String message, String errorCode) {
        super(message, null, errorCode);
    }

    /**
     * Instantiates a new business exception.
     *
     * @param cause the cause
     * @param errorCode the error code
     * @param args the args
     */
//    public BusinessException(Throwable cause, String errorCode, Object... args) {
//        //super(mu.getMessage(errorCode, args), cause, errorCode);
//    }
    /**
     * Instantiates a new business exception.
     *
     * @param errorCode the error code
     * @param args the args
     */
//    public BusinessException(String errorCode, Object... args) {
//        super(mu.getMessage(errorCode, args), null, errorCode);
//    }
}
