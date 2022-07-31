package PaymentAdapter;

//the adapter
//step-1 create common adapter interface
public interface PaymentProviderI {
    String generateLink();
    void pay(PaymentReq paymentReq);
    PaymentStatus checkStatus();
}
