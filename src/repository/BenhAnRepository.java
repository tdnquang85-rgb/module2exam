package repository;

import model.BenhAn;
import model.BenhAnThuong;
import model.BenhAnVip;

import java.io.*;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;

/**
 * Lớp triển khai IBenhAnRepository để lưu trữ và truy xuất bệnh án
 * từ file CSV duy nhất data/medical_records.csv.
 */
public class BenhAnRepository implements IBenhAnRepository {
    private static final String FILE_PATH = "data/medical_records.csv";

    public BenhAnRepository() {
        initFile();
    }

    /**
     * Khởi tạo file và thư mục data nếu chưa tồn tại.
     */
    private void initFile() {
        File file = new File(FILE_PATH);
        File parentDir = file.getParentFile();
        if (parentDir != null && !parentDir.exists()) {
            parentDir.mkdirs();
        }
        if (!file.exists()) {
            try {
                file.createNewFile();
            } catch (IOException e) {
                System.err.println("Không thể khởi tạo file: " + e.getMessage());
            }
        }
    }

    @Override
    public ArrayList<BenhAn> findAll() {
        ArrayList<BenhAn> list = new ArrayList<>();
        File file = new File(FILE_PATH);
        if (!file.exists() || file.length() == 0) {
            return list;
        }

        try (BufferedReader reader = new BufferedReader(
                new InputStreamReader(new FileInputStream(file), StandardCharsets.UTF_8))) {
            String line;
            while ((line = reader.readLine()) != null) {
                line = line.trim();
                if (line.isEmpty() || line.startsWith("#") || line.startsWith("soThuTu")) {
                    continue;
                }
                String[] parts = line.split(",", -1);
                try {
                    int stt = Integer.parseInt(parts[0].trim());
                    String maBA = parts[1].trim();
                    String maBN = parts[2].trim();
                    String tenBN = parts[3].trim();
                    String ngayNhap = parts[4].trim();
                    String ngayRa = parts[5].trim();
                    String lyDo = parts[6].trim();

                    if (parts.length == 8) {
                        // Bệnh án thường
                        double phiNamVien = Double.parseDouble(parts[7].trim());
                        BenhAnThuong benhAnThuong = new BenhAnThuong(
                                stt, maBA, maBN, tenBN, ngayNhap, ngayRa, lyDo, phiNamVien
                        );
                        list.add(benhAnThuong);
                    } else if (parts.length == 9) {
                        // Bệnh án VIP
                        String loaiVip = parts[7].trim();
                        String thoiHanVip = parts[8].trim();
                        BenhAnVip benhAnVip = new BenhAnVip(
                                stt, maBA, maBN, tenBN, ngayNhap, ngayRa, lyDo, loaiVip, thoiHanVip
                        );
                        list.add(benhAnVip);
                    }
                } catch (Exception ex) {
                    System.err.println("Bỏ qua dòng dữ liệu không đúng định dạng: " + line);
                }
            }
        } catch (IOException e) {
            System.err.println("Lỗi khi đọc file medical_records.csv: " + e.getMessage());
        }

        return list;
    }

    @Override
    public void saveAll(ArrayList<BenhAn> list) {
        initFile();
        try (BufferedWriter writer = new BufferedWriter(
                new OutputStreamWriter(new FileOutputStream(FILE_PATH, false), StandardCharsets.UTF_8))) {
            for (BenhAn ba : list) {
                writer.write(ba.toCsvString());
                writer.newLine();
            }
        } catch (IOException e) {
            System.err.println("Lỗi khi ghi file medical_records.csv: " + e.getMessage());
        }
    }

    @Override
    public void add(BenhAn benhAn) {
        ArrayList<BenhAn> list = findAll();
        list.add(benhAn);
        saveAll(list);
    }
}
