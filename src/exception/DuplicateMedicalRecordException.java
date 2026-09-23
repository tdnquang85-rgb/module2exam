package exception;

/**
 * Ngoại lệ được ném ra khi mã bệnh án đã tồn tại trong hệ thống.
 */
public class DuplicateMedicalRecordException extends Exception {
    public DuplicateMedicalRecordException() {
        super("Bệnh án đã tồn tại.");
    }

    public DuplicateMedicalRecordException(String message) {
        super(message);
    }
}
