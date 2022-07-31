package CustomExceptions;

import lombok.Getter;

@Getter
public enum ErrorCode {
    //you can even have more fields for detailed error like:
    //ErrorCode(int code, String desc){this.CODE = code; this.desc = desc;}
    INVALID_RP_MODE(405),
    INVALID_CF_MODE(406),
    CF_PAYMENT_FAILED(407),
    RP_PAYMENT_FAILED(408);

    private int CODE;

    ErrorCode(int code){
        this.CODE = code;
    }

    public int getCODE() {
        return CODE;
    }
}
