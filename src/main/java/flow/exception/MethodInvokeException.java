package flow.exception;

import flow.exception.code.ErrorCode;
import flow.exception.code.ErrorType;

public class MethodInvokeException extends CoreBaseException{

    private static final ErrorType ERROR_TYPE = ErrorType.ERROR_METHOD_INVOKE;

    public MethodInvokeException(ErrorCode errorCode) {
        super(errorCode, ERROR_TYPE);
    }

    public MethodInvokeException(String message, ErrorCode errorCode) {
        super(message, errorCode, ERROR_TYPE);
    }

    public MethodInvokeException(String message, Throwable cause, ErrorCode errorCode) {
        super(message, cause, errorCode, ERROR_TYPE);
    }

    public MethodInvokeException(Throwable cause, ErrorCode errorCode) {
        super(cause, errorCode, ERROR_TYPE);
    }

}
