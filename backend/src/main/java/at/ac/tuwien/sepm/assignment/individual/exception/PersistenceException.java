package at.ac.tuwien.sepm.assignment.individual.exception;

public class PersistenceException extends RuntimeException {
    private static final long serialVersionUID = -5762187655103128692L;

    public PersistenceException(String message) {
        super(message);
    }

    public PersistenceException(Throwable cause) {
        super(cause);
    }

    public PersistenceException(String message, Throwable cause) {
        super(message, cause);
    }
}
