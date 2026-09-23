package util;

public class Validator {

    public static final String MA_BENH_AN_REGEX = "^BA-\\d+$";

    public static final String MA_BENH_NHAN_REGEX = "^BN-\\d+$";

    public static boolean isValidMaBenhAn(String maBenhAn) {
        return maBenhAn != null && maBenhAn.trim().matches(MA_BENH_AN_REGEX);
    }

    public static boolean isValidMaBenhNhan(String maBenhNhan) {
        return maBenhNhan != null && maBenhNhan.trim().matches(MA_BENH_NHAN_REGEX);
    }

    public static boolean isValidTen(String ten) {
        if (ten == null || ten.trim().isEmpty()) {
            return false;
        }

        return ten.trim().matches("^[\\p{L}\\s]+$");
    }

    public static boolean isValidPhiNamVien(double phi) {
        return phi > 0;
    }

    public static boolean isValidLoaiVip(String loaiVip) {
        if (loaiVip == null)
            return false;
        String trimmed = loaiVip.trim().toUpperCase();
        return trimmed.equals("VIP I") || trimmed.equals("VIP II") || trimmed.equals("VIP III");
    }
}
