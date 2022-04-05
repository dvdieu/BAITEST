package core;

public class BillingHasBeenPaidException extends BillingPaidException {
    String billID;

    public String getBillID() {
        return billID;
    }

    public BillingHasBeenPaidException(String s, String billID) {
        super(s);
        this.billID = billID;
    }

    public BillingHasBeenPaidException(String s) {
        super(s);
    }
}
