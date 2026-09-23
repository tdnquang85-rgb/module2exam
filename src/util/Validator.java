package util;

/**
 * Lớp tiện ích kiểm tra tính hợp lệ của dữ liệu đầu vào.
 */
public class Validator {
    // Định dạng: BA- theo sau bởi một hoặc nhiều chữ số (ví dụ: BA-001, BA-1)
    public static final String MA_BENH_AN_REGEX = "^BA-\\d+$";
    // Định dạng: BN- theo sau bởi một hoặc nhiều chữ số (ví dụ: BN-001, BN-1)
    public static final String MA_BENH_NHAN_REGEX = "^BN-\\d+$";

    /**
     * Kiểm tra định dạng mã bệnh án.
     */
    public static boolean isValidMaBenhAn(String maBenhAn) {
        return maBenhAn != null && maBenhAn.trim().matches(MA_BENH_AN_REGEX);
    }

    /**
     * Kiểm tra định dạng mã bệnh nhân.
     */
    public static boolean isValidMaBenhNhan(String maBenhNhan) {
        return maBenhNhan != null && maBenhNhan.trim().matches(MA_BENH_NHAN_REGEX);
    }

    /**
     * Kiểm tra tên bệnh nhân: không rỗng và chỉ chứa chữ cái cùng dấu cách.
     */
    public static boolean isValidTen(String ten) {
        if (ten == null || ten.trim().isEmpty()) {
            return false;
        }
        // Cho phép chữ cái tiếng Việt (unicode) và dấu cách
        return ten.trim().matches("^[\\p{L}\\s]+$");
    }

    /**
     * Kiểm tra phí nằm viện: phải lớn hơn 0.
     */
    public static boolean isValidPhiNamVien(double phi) {
        return phi > 0;
    }

    /**
     * Kiểm tra loại VIP hợp lệ: VIP I, VIP II, VIP III.
     */
    public static boolean isValidLoaiVip(String loaiVip) {
        if (loaiVip == null) return false;
        String trimmed = loaiVip.trim().toUpperCase();
        return trimmed.equals("VIP I") || trimmed.equals("VIP II") || trimmed.equals("VIP III");
    }
}
