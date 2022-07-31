import CustomExceptions.ErrorCode;
import CustomExceptions.IllegalPayModeException;
import PaymentAdapter.PaymentProviderI;
import PaymentAdapter.PaymentReq;
import PaymentAdapter.PaymentStatus;
import external.RazorPay.RPStat;
import external.RazorPay.RPTypes;
import external.RazorPay.RazorPayAPI;

//implementation of the adapter/ interface -> step 2: concrete adapters -> the API user/ client
public class RazorPayProvider implements PaymentProviderI {

    private RazorPayAPI razorPayAPI;

    public RazorPayProvider(){
        razorPayAPI = RazorPayAPI.getRazorPayAPI();
    }

    @Override
    public String generateLink() {
        return razorPayAPI.rpLink();
    }

    //this is how server handles the client-side exception
    @Override
    public void pay(PaymentReq paymentReq) throws IllegalPayModeException {
        razorPayAPI.Init4Pay();
        try {
            razorPayAPI.makePayment(RPTypes.valueOf(paymentReq.getPaymentMode()),  paymentReq.getAmount());
        } catch (IllegalArgumentException e){
            throw new IllegalPayModeException(e.getMessage(), e.getCause(), ErrorCode.INVALID_RP_MODE);
        }
    }

    // Handling different Return Type or Arguments from API Implementation of Adapter
    // and merging them / them following to single abstraction or adapter resulting into a structure.
    @Override
    public PaymentStatus checkStatus() {
        if (razorPayAPI.verifyStatus().equals(RPStat.OK))
            return PaymentStatus.SUCCESS;
        return PaymentStatus.FAILURE;
    }
}
