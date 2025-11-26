package app.checkout.exception;

public class OrderProcessingException extends ServiceException {

    public OrderProcessingException(String message) {
        super(400, message);
    }

    public OrderProcessingException(String message, Throwable cause) {
        super(400, message, cause);
    }
}
