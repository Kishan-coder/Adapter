package CustomExceptions;

public class RPayFailedException extends RuntimeException{
    public RPayFailedException(String message, Throwable cause, ErrorCode errorCode){
        super(message, cause);
    }
    ErrorCode getErr(){
        return ErrorCode.RP_PAYMENT_FAILED;
    }
}
