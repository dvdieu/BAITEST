package domain.model.application.command;

public class NewPaymentCommand {
    String billingId;

    public NewPaymentCommand(String billingId) {
        this.billingId = billingId;
    }

    public NewPaymentCommand() {
    }

    public String getBillingId() {
        return billingId;
    }

    public void setBillingId(String billingId) {
        this.billingId = billingId;
    }
}
