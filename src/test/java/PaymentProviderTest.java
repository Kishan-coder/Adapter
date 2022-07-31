import CustomExceptions.ErrorCode;
import CustomExceptions.IllegalPayModeException;
import PaymentAdapter.PaymentProviderI;
import PaymentAdapter.PaymentReq;
import PaymentAdapter.PaymentStatus;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class PaymentProviderTest {

    PaymentProviderI paymentProvider;

    @Test
    public void testRazorPayAPI(){
        paymentProvider = new RazorPayProvider();
        assertTrue(paymentProvider instanceof RazorPayProvider, "Test failed due to different instance than RazorPay");
    }

    @Test
    public void testCashFreeAPI(){
        paymentProvider = new CashFreeProvider();
        Assertions.assertEquals(PaymentStatus.SUCCESS, paymentProvider.checkStatus());
    }

    @Test
    public void testUpdatedPaymentLogic_RP(){
        PaymentReq  paymentReq = new PaymentReq.PayReqBuilder().withPayInfo("UPI", 10).withPayNote("Cafe").buildPayReq();
        paymentProvider = new RazorPayProvider();
        Assertions.assertDoesNotThrow(() -> paymentProvider.pay(paymentReq));
    }

    @Test
    public void testUpdatedPaymentLogic_RP2(){
        PaymentReq  paymentReq = new PaymentReq.PayReqBuilder().withPayInfo("NET_BANKING", 10).withPayNote("Cafe").buildPayReq();
        paymentProvider = new RazorPayProvider();
        IllegalPayModeException thrown = Assertions.assertThrows(
                IllegalPayModeException.class,
                () -> paymentProvider.pay(paymentReq),
                "Expected pay method to throw, but it didn't!"
        );

        assertTrue(thrown.getErr().equals(ErrorCode.INVALID_RP_MODE));
    }
}
