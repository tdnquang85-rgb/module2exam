package repository;

import model.BenhAn;
import java.util.ArrayList;

/**
 * Interface cho việc thao tác dữ liệu Bệnh án với tệp tin.
 */
public interface IBenhAnRepository {
    /**
     * Lấy toàn bộ danh sách bệnh án từ file CSV.
     */
    ArrayList<BenhAn> findAll();

    /**
     * Lưu toàn bộ danh sách bệnh án vào file CSV.
     */
    void saveAll(ArrayList<BenhAn> list);

    /**
     * Thêm một bệnh án mới vào file.
     */
    void add(BenhAn benhAn);
}
