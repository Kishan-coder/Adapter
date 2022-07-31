package CustomExceptions;

public class IllegalPayModeException extends IllegalArgumentException{
    private ErrorCode errorCode;
    public IllegalPayModeException(String message, Throwable cause, ErrorCode errorCode){
        super(message, cause);
        this.errorCode = errorCode;
    }
    public ErrorCode getErr(){
        return errorCode;
    }
}
