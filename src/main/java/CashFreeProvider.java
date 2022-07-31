import PaymentAdapter.PaymentProviderI;
import PaymentAdapter.PaymentReq;
import PaymentAdapter.PaymentStatus;
import external.CashFree.CFCheck;
import external.CashFree.CashFreeAPI;

//An implementation of adapter for the 3rd party API

public class CashFreeProvider implements PaymentProviderI {
    public CashFreeProvider(){
        cashFreeAPI = CashFreeAPI.getCashFreeAPI();
    }

    private CashFreeAPI cashFreeAPI;
    @Override
    public String generateLink() {
        return cashFreeAPI.makeCFLink();
    }

    @Override
    public void pay(PaymentReq paymentReq) {
        cashFreeAPI.processPay(paymentReq.getPaymentMode(), paymentReq.getName(), paymentReq.getAmount());
    }

    // Handling different Return Type or Arguments from API Implementation in Adapter
    // and merging them into single abstraction resulting into a structure.
    @Override
    public PaymentStatus checkStatus() {
        if (cashFreeAPI.checkPayStatus().equals(CFCheck.DONE))
            return PaymentStatus.SUCCESS;
        else
            return PaymentStatus.FAILURE;
    }
}
