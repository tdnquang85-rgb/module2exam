package model;

/**
 * Lớp đại diện cho Bệnh án thường, kế thừa từ BenhAn.
 */
public class BenhAnThuong extends BenhAn {
    private double phiNamVien;

    public BenhAnThuong() {
        super();
    }

    public BenhAnThuong(int soThuTu, String maBenhAn, String maBenhNhan, String tenBenhNhan,
                        String ngayNhapVien, String ngayRaVien, String lyDoNhapVien, double phiNamVien) {
        super(soThuTu, maBenhAn, maBenhNhan, tenBenhNhan, ngayNhapVien, ngayRaVien, lyDoNhapVien);
        this.phiNamVien = phiNamVien;
    }

    public double getPhiNamVien() {
        return phiNamVien;
    }

    public void setPhiNamVien(double phiNamVien) {
        this.phiNamVien = phiNamVien;
    }

    @Override
    public String toCsvString() {
        return String.join(",",
                String.valueOf(getSoThuTu()),
                getMaBenhAn(),
                getMaBenhNhan(),
                getTenBenhNhan(),
                getNgayNhapVien(),
                getNgayRaVien(),
                getLyDoNhapVien(),
                String.format("%.0f", phiNamVien)
        );
    }

    @Override
    public String getLoaiBenhAn() {
        return "Bệnh án thường";
    }

    @Override
    public String getThongTinRieng() {
        return String.format("Loại: Thường | Phí nằm viện: %,.0f VNĐ", phiNamVien);
    }
}
