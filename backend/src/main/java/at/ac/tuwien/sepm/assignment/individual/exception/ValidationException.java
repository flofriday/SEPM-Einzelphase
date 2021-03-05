package at.ac.tuwien.sepm.assignment.individual.exception;

public class ValidationException extends RuntimeException {
    private static final long serialVersionUID = -5437362130486703382L;

    public ValidationException(String message) {
        super(message);
    }
}
