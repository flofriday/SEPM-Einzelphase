package at.ac.tuwien.sepm.assignment.individual.exception;

public class NotFoundException extends RuntimeException {
    private static final long serialVersionUID = -7766710819642880317L;

    public NotFoundException(String message) {
        super(message);
    }
}
