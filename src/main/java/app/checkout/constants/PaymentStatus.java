package app.checkout.constants;

public enum PaymentStatus {
    PENDING("PENDING", "Payment is pending"),
    PAID("PAID", "Payment has been completed"),
    FAILED("FAILED", "Payment has failed"),
    REFUNDED("REFUNDED", "Payment has been refunded"),
    EXPIRED("EXPIRED", "Payment has expired");

    private final String status;
    private final String description;

    PaymentStatus(String status, String description) {
        this.status = status;
        this.description = description;
    }

    public String getStatus() {
        return status;
    }

    public String getDescription() {
        return description;
    }

    public static PaymentStatus fromString(String status) {
        for (PaymentStatus ps : PaymentStatus.values()) {
            if (ps.status.equalsIgnoreCase(status)) {
                return ps;
            }
        }
        throw new IllegalArgumentException("Invalid payment status: " + status);
    }
}