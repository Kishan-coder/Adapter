package external.RazorPay;

public class RazorPayAPI {
    private  RazorPayAPI(){}
    private static RazorPayAPI razorPayAPI;
    static{
        razorPayAPI = new RazorPayAPI();
    }
    public static RazorPayAPI getRazorPayAPI(){
        return razorPayAPI;
    }
    public String rpLink(){
        return "rp.pubPay.com";
    }
    public void Init4Pay(){
        System.out.println("Initializing Payment Processing...");
    }
    public void makePayment(RPTypes rpTypes, int amount){
        System.out.println(String.format("Processing the payment of $ %d with payment mode %s", amount, rpTypes.name()));
    }
    public RPStat verifyStatus(){
        return RPStat.OK;
    }
}
