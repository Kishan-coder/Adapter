package external.CashFree;

public class CashFreeAPI {
    private  CashFreeAPI(){}
    private static CashFreeAPI cashFreeAPI;
    static{
        cashFreeAPI = new CashFreeAPI();
    }
    public static CashFreeAPI getCashFreeAPI(){
        return cashFreeAPI;
    }
    public String makeCFLink(){
        return "cf.pubPay.com";
    }
    public void processPay(String mode, String name, int amount){
        System.out.println(String.format("Processing the %s payment of Rs. %d made via %s in Cash Free servers",
                name, amount, mode));
    }
    public CFCheck checkPayStatus(){
        if (null != this)
            return CFCheck.DONE;
        else
            return CFCheck.FAILED;
    }
}
