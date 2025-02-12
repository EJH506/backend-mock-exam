package jihye.backend_mock_exam.exception;

public class DuplicateAccountIdException extends RuntimeException {
    public DuplicateAccountIdException(String message) {
        super(message);
    }
}
