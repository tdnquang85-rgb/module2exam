package view;

import exception.DuplicateMedicalRecordException;
import model.BenhAn;
import model.BenhAnThuong;
import model.BenhAnVip;
import service.IBenhAnService;
import util.DateUtil;
import util.Validator;

import java.util.ArrayList;
import java.util.Scanner;

/**
 * Lớp hiển thị giao diện console thuần văn bản và nhận dữ liệu từ người dùng.
 */
public class BenhAnView {
    private final Scanner scanner;

    public BenhAnView() {
        this.scanner = new Scanner(System.in);
    }

    /**
     * Hiển thị menu chính chính xác theo yêu cầu.
     */
    public void displayMainMenu() {
        System.out.println("1.\tThêm mới");
        System.out.println("2.\tXoá");
        System.out.println("3.\tXem danh sách các bệnh án");
        System.out.println("4.\tThoát");
        System.out.print("Chọn chức năng: ");
    }

    public String inputString() {
        return scanner.nextLine().trim();
    }

    /**
     * Nhận lựa chọn chức năng chính.
     */
    public int getChoice() {
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                return Integer.parseInt(input);
            } catch (NumberFormatException e) {
                System.out.print("Lựa chọn không hợp lệ. Vui lòng nhập lại: ");
            }
        }
    }

    /**
     * Giao diện thêm mới bệnh án.
     */
    public BenhAn inputBenhAn(IBenhAnService service) {
        System.out.println("\n--- CHỌN LOẠI BỆNH ÁN CẦN THÊM MỚI ---");
        System.out.println("1.\tBệnh án thường");
        System.out.println("2.\tBệnh án VIP");
        System.out.println("3.\tQuay lại menu chính");
        System.out.print("Chọn loại bệnh án: ");

        int typeChoice;
        while (true) {
            try {
                String input = scanner.nextLine().trim();
                typeChoice = Integer.parseInt(input);
                if (typeChoice >= 1 && typeChoice <= 3) {
                    break;
                }
                System.out.print("Vui lòng chọn 1, 2 hoặc 3: ");
            } catch (NumberFormatException e) {
                System.out.print("Vui lòng nhập số hợp lệ: ");
            }
        }

        if (typeChoice == 3) {
            return null; // Quay lại
        }

        System.out.println("\n--- NHẬP THÔNG TIN BỆNH ÁN ---");

        // 1. Số thứ tự: Tự động tăng
        int soThuTu = service.getNextSoThuTu();
        System.out.println("Số thứ tự bệnh án (tự động): " + soThuTu);

        // 2. Mã bệnh án: Gợi ý theo quy tắc BA-số thứ tự, validate regex và kiểm tra trùng lặp
        String suggestedMaBA = service.generateSuggestedMaBenhAn();
        String maBenhAn;
        while (true) {
            System.out.printf("Nhập mã bệnh án (nhấn Enter để dùng '%s' hoặc nhập định dạng BA-xxx): ", suggestedMaBA);
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                input = suggestedMaBA;
            }

            if (!Validator.isValidMaBenhAn(input)) {
                System.out.println("Mã bệnh án không đúng định dạng (phải bắt đầu bằng 'BA-' và theo sau là số, ví dụ: " + suggestedMaBA + "). Vui lòng nhập lại!");
                continue;
            }

            try {
                service.checkDuplicateMaBenhAn(input);
                maBenhAn = input;
                break;
            } catch (DuplicateMedicalRecordException e) {
                System.out.println(e.getMessage() + " Vui lòng nhập lại!");
            }
        }

        // 3. Mã bệnh nhân: Gợi ý theo quy tắc BN-số thứ tự, validate regex
        String suggestedMaBN = service.generateSuggestedMaBenhNhan();
        String maBenhNhan;
        while (true) {
            System.out.printf("Nhập mã bệnh nhân (nhấn Enter để dùng '%s' hoặc nhập định dạng BN-xxx): ", suggestedMaBN);
            String input = scanner.nextLine().trim();
            if (input.isEmpty()) {
                input = suggestedMaBN;
            }

            if (!Validator.isValidMaBenhNhan(input)) {
                System.out.println("Mã bệnh nhân không đúng định dạng (phải bắt đầu bằng 'BN-' và theo sau là số, ví dụ: " + suggestedMaBN + "). Vui lòng nhập lại!");
                continue;
            }
            maBenhNhan = input;
            break;
        }

        // 4. Tên bệnh nhân
        String tenBenhNhan;
        while (true) {
            System.out.print("Nhập tên bệnh nhân: ");
            tenBenhNhan = scanner.nextLine().trim();
            if (Validator.isValidTen(tenBenhNhan)) {
                break;
            }
            System.out.println("Tên bệnh nhân không hợp lệ (không được để trống và chỉ chứa chữ cái). Vui lòng nhập lại!");
        }

        // 5. Ngày nhập viện
        String ngayNhapVien;
        while (true) {
            System.out.print("Nhập ngày nhập viện (định dạng dd/MM/yyyy): ");
            ngayNhapVien = scanner.nextLine().trim();
            if (DateUtil.isValidDate(ngayNhapVien)) {
                break;
            }
            System.out.println("Ngày nhập viện không hợp lệ! Vui lòng nhập đúng định dạng ngày dd/MM/yyyy (ví dụ: 01/10/2026).");
        }

        // 6. Ngày ra viện: >= ngày nhập viện
        String ngayRaVien;
        while (true) {
            System.out.print("Nhập ngày ra viện (định dạng dd/MM/yyyy): ");
            ngayRaVien = scanner.nextLine().trim();
            if (!DateUtil.isValidDate(ngayRaVien)) {
                System.out.println("Ngày ra viện không hợp lệ! Vui lòng nhập đúng định dạng ngày dd/MM/yyyy.");
                continue;
            }
            if (!DateUtil.isEndAfterOrEqualStart(ngayNhapVien, ngayRaVien)) {
                System.out.println("Ngày ra viện phải lớn hơn hoặc bằng ngày nhập viện (" + ngayNhapVien + "). Vui lòng nhập lại!");
                continue;
            }
            break;
        }

        // 7. Lý do nhập viện
        String lyDoNhapVien;
        while (true) {
            System.out.print("Nhập lý do nhập viện: ");
            lyDoNhapVien = scanner.nextLine().trim();
            if (!lyDoNhapVien.isEmpty()) {
                break;
            }
            System.out.println("Lý do nhập viện không được để trống. Vui lòng nhập lại!");
        }

        // Thuộc tính riêng cho từng loại bệnh án
        if (typeChoice == 1) {
            // Bệnh án thường: Phí nằm viện
            double phiNamVien;
            while (true) {
                System.out.print("Nhập phí nằm viện (VNĐ > 0): ");
                try {
                    String input = scanner.nextLine().trim();
                    phiNamVien = Double.parseDouble(input);
                    if (Validator.isValidPhiNamVien(phiNamVien)) {
                        break;
                    }
                    System.out.println("Phí nằm viện phải lớn hơn 0!");
                } catch (NumberFormatException e) {
                    System.out.println("Phí nằm viện phải là một số hợp lệ!");
                }
            }
            return new BenhAnThuong(soThuTu, maBenhAn, maBenhNhan, tenBenhNhan,
                    ngayNhapVien, ngayRaVien, lyDoNhapVien, phiNamVien);

        } else {
            // Bệnh án VIP: Loại VIP, Thời hạn VIP
            System.out.println("Chọn gói VIP:");
            System.out.println("1.\tVIP I");
            System.out.println("2.\tVIP II");
            System.out.println("3.\tVIP III");
            System.out.print("Chọn gói (1-3): ");
            String loaiVip;
            while (true) {
                String sub = scanner.nextLine().trim();
                if ("1".equals(sub)) {
                    loaiVip = "VIP I";
                    break;
                } else if ("2".equals(sub)) {
                    loaiVip = "VIP II";
                    break;
                } else if ("3".equals(sub)) {
                    loaiVip = "VIP III";
                    break;
                }
                System.out.print("Lựa chọn không hợp lệ. Vui lòng chọn 1, 2 hoặc 3: ");
            }

            String thoiHanVip;
            while (true) {
                System.out.print("Nhập thời hạn VIP (định dạng dd/MM/yyyy): ");
                thoiHanVip = scanner.nextLine().trim();
                if (DateUtil.isValidDate(thoiHanVip)) {
                    break;
                }
                System.out.println("Thời hạn VIP không hợp lệ! Vui lòng nhập đúng định dạng ngày dd/MM/yyyy.");
            }

            return new BenhAnVip(soThuTu, maBenhAn, maBenhNhan, tenBenhNhan,
                    ngayNhapVien, ngayRaVien, lyDoNhapVien, loaiVip, thoiHanVip);
        }
    }

    /**
     * Nhận mã bệnh án cần xoá.
     */
    public String inputMaBenhAnToDelete() {
        System.out.print("Nhập mã bệnh án cần xoá: ");
        return scanner.nextLine().trim();
    }

    /**
     * Xác nhận xoá bệnh án.
     */
    public boolean confirmDelete(BenhAn benhAn) {
        System.out.println("\nThông tin bệnh án cần xoá:");
        System.out.println(benhAn);
        System.out.print("Bạn có chắc chắn muốn xoá bệnh án này không? (Yes/No hoặc Y/N): ");
        String answer = scanner.nextLine().trim().toLowerCase();
        return answer.equals("yes") || answer.equals("y");
    }

    /**
     * Hiển thị danh sách toàn bộ bệnh án.
     */
    public void displayMedicalRecords(ArrayList<BenhAn> list) {
        System.out.println("\n========================================== DANH SÁCH BỆNH ÁN ==========================================");
        if (list == null || list.isEmpty()) {
            System.out.println("Danh sách bệnh án hiện đang trống.");
        } else {
            for (BenhAn ba : list) {
                System.out.println(ba);
            }
            System.out.printf("Tổng số bệnh án: %d\n", list.size());
        }
        System.out.println("=======================================================================================================\n");
    }

    /**
     * Hiển thị thông báo.
     */
    public void showMessage(String message) {
        System.out.println(message);
    }
}
