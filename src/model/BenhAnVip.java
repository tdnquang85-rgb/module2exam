package model;

/**
 * Lớp đại diện cho Bệnh án VIP, kế thừa từ BenhAn.
 */
public class BenhAnVip extends BenhAn {
    private String loaiVip;     // "VIP I", "VIP II", "VIP III"
    private String thoiHanVip;  // Định dạng dd/MM/yyyy

    public BenhAnVip() {
        super();
    }

    public BenhAnVip(int soThuTu, String maBenhAn, String maBenhNhan, String tenBenhNhan,
                     String ngayNhapVien, String ngayRaVien, String lyDoNhapVien,
                     String loaiVip, String thoiHanVip) {
        super(soThuTu, maBenhAn, maBenhNhan, tenBenhNhan, ngayNhapVien, ngayRaVien, lyDoNhapVien);
        this.loaiVip = loaiVip;
        this.thoiHanVip = thoiHanVip;
    }

    public String getLoaiVip() {
        return loaiVip;
    }

    public void setLoaiVip(String loaiVip) {
        this.loaiVip = loaiVip;
    }

    public String getThoiHanVip() {
        return thoiHanVip;
    }

    public void setThoiHanVip(String thoiHanVip) {
        this.thoiHanVip = thoiHanVip;
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
                loaiVip,
                thoiHanVip
        );
    }

    @Override
    public String getLoaiBenhAn() {
        return "Bệnh án VIP";
    }

    @Override
    public String getThongTinRieng() {
        return String.format("Loại: %s | Thời hạn VIP: %s", loaiVip, thoiHanVip);
    }
}
