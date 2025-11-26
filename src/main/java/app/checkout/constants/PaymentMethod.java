package app.checkout.constants;

public enum PaymentMethod {
    CREDIT_CARD("CREDIT_CARD", "Credit Card"),
    BANK_TRANSFER("BANK_TRANSFER", "Bank Transfer"),
    E_WALLET("E_WALLET", "E-Wallet"),
    COD("COD", "Cash on Delivery");

    private final String method;
    private final String displayName;

    PaymentMethod(String method, String displayName) {
        this.method = method;
        this.displayName = displayName;
    }

    public String getMethod() {
        return method;
    }

    public String getDisplayName() {
        return displayName;
    }

    public static PaymentMethod fromString(String method) {
        for (PaymentMethod pm : PaymentMethod.values()) {
            if (pm.method.equalsIgnoreCase(method)) {
                return pm;
            }
        }
        throw new IllegalArgumentException("Invalid payment method: " + method);
    }

    public static boolean isValid(String method) {
        try {
            fromString(method);
            return true;
        } catch (IllegalArgumentException e) {
            return false;
        }
    }
}