package core;

public abstract class BillingPaidException extends Exception {
    public BillingPaidException(String s) {
        super(s);
    }
}
