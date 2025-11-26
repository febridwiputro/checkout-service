package app.checkout.exception;

public class PaymentFailedException extends ServiceException {

    public PaymentFailedException(String message) {
        super(402, message);
    }

    public PaymentFailedException(String message, Throwable cause) {
        super(402, message, cause);
    }
}
