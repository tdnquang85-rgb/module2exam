package service;

import exception.DuplicateMedicalRecordException;
import model.BenhAn;

import java.util.ArrayList;

/**
 * Interface định nghĩa các nghiệp vụ quản lý bệnh án.
 */
public interface IBenhAnService {
    /**
     * Lấy danh sách toàn bộ bệnh án.
     */
    ArrayList<BenhAn> getAll();

    /**
     * Thêm mới một bệnh án.
     * @throws DuplicateMedicalRecordException nếu mã bệnh án đã tồn tại.
     */
    void add(BenhAn benhAn) throws DuplicateMedicalRecordException;

    /**
     * Xoá bệnh án theo mã bệnh án.
     * @return true nếu xoá thành công, false nếu không tìm thấy.
     */
    boolean delete(String maBenhAn);

    /**
     * Tìm bệnh án theo mã bệnh án.
     */
    BenhAn findByMaBenhAn(String maBenhAn);

    /**
     * Kiểm tra xem mã bệnh án đã tồn tại hay chưa.
     */
    boolean existsByMaBenhAn(String maBenhAn);

    /**
     * Kiểm tra trùng lặp mã bệnh án và ném ngoại lệ nếu đã tồn tại.
     */
    void checkDuplicateMaBenhAn(String maBenhAn) throws DuplicateMedicalRecordException;

    /**
     * Lấy số thứ tự kế tiếp (số thứ tự mới = số thứ tự cuối trong danh sách + 1).
     */
    int getNextSoThuTu();

    /**
     * Tự động sinh mã bệnh án gợi ý theo số thứ tự mới: BA-xxx.
     */
    String generateSuggestedMaBenhAn();

    /**
     * Tự động sinh mã bệnh nhân gợi ý theo số thứ tự mới: BN-xxx.
     */
    String generateSuggestedMaBenhNhan();
}
