package app.checkout.exception;

public class InvalidPaymentMethodException extends ServiceException {

    public InvalidPaymentMethodException(String paymentMethod) {
        super(400, String.format("Invalid payment method: %s", paymentMethod));
    }
}
