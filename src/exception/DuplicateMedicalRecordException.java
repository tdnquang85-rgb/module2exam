package exception;

public class DuplicateMedicalRecordException extends Exception {
    public DuplicateMedicalRecordException() {
        super("Bệnh án đã tồn tại.");
    }

    public DuplicateMedicalRecordException(String message) {
        super(message);
    }
}
