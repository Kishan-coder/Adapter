package PaymentAdapter;

import lombok.Getter;

import java.util.Arrays;
import java.util.Random;

//the builder pattern in action
//we need immutable payment request with proper validation for creating instance

@Getter
public class PaymentReq {
    private PaymentReq(){}

    String paymentMode;
    String name;
    int amount;
    Long transactionRef;

    public String getPaymentMode() {
        return paymentMode;
    }

    public String getName() {
        return name;
    }

    public int getAmount() {
        return amount;
    }

    public Long getTransactionRef() {
        return transactionRef;
    }

    //String currency;
    public static class PayReqBuilder{
        private PaymentReq paymentReq = new PaymentReq();
        public PayReqBuilder withPayNote(String name){
            paymentReq.name = name;
            return this;
        }
        public PayReqBuilder withPayInfo(String mode, int amount){
            paymentReq.paymentMode = mode;
            paymentReq.amount = amount;
            paymentReq.transactionRef = new Random().nextLong();
            return this;
        }
        private boolean validate(){
           if (!Arrays.stream(new String[]{"UPI", "NET_BANKING", "DEBIT", "CREDIT", "SLICE", "CHEQUE"}).anyMatch(mode -> (mode.equals(paymentReq.paymentMode))))
               return false;
           if (paymentReq.amount <= 0)
               return false;
           return true;
        }
        public PaymentReq buildPayReq(){
            if (!validate())
                return null;
            return paymentReq;
        }
    }
}
