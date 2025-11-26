package app.checkout.exception;

public class ValidationException extends ServiceException {

    public ValidationException(String message) {
        super(400, message);
    }

    public ValidationException(String field, String message) {
        super(400, String.format("Validation error on field '%s': %s", field, message));
    }
}
