package flow.exception;

import flow.exception.code.ErrorCode;
import flow.exception.code.ErrorType;

public class HandlerCreateException extends CoreBaseException{

    private static final ErrorType ERROR_TYPE = ErrorType.HANDLER_CREATE_EXCEPTION;

    public HandlerCreateException(ErrorCode errorCode) {
        super(errorCode, ERROR_TYPE);
    }

    public HandlerCreateException(String message, ErrorCode errorCode) {
        super(message, errorCode, ERROR_TYPE);
    }

    public HandlerCreateException(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause, errorCode, ERROR_TYPE);
    }

    public HandlerCreateException(Throwable cause, ErrorCode errorCode) {
        super(cause, errorCode, ERROR_TYPE);
    }
}
