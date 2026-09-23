package controller;

import exception.DuplicateMedicalRecordException;
import model.BenhAn;
import service.BenhAnService;
import service.IBenhAnService;
import view.BenhAnView;

import java.util.ArrayList;

public class BenhAnController {
    private final IBenhAnService service;
    private final BenhAnView view;

    public BenhAnController() {
        this.service = new BenhAnService();
        this.view = new BenhAnView();
    }

    public BenhAnController(IBenhAnService service, BenhAnView view) {
        this.service = service;
        this.view = view;
    }

    public void run() {
        boolean running = true;
        while (running) {
            view.displayMainMenu();
            int choice = view.getChoice();

            switch (choice) {
                case 1:
                    handleAddNew();
                    break;
                case 2:
                    handleDelete();
                    break;
                case 3:
                    handleDisplayAll();
                    break;
                case 4:
                    view.showMessage("Đã thoát ứng dụng. Tạm biệt!");
                    running = false;
                    break;
                default:
                    view.showMessage("Chức năng không tồn tại. Vui lòng chọn từ 1 đến 4!\n");
                    break;
            }
        }
    }

    private void handleAddNew() {
        BenhAn benhAn = view.inputBenhAn(service);
        if (benhAn != null) {
            try {
                service.add(benhAn);
                view.showMessage("\n==> Thêm mới bệnh án thành công!\n");
            } catch (DuplicateMedicalRecordException e) {
                view.showMessage("\n[LỖI] " + e.getMessage() + "\n");
            }
        }
    }

    private void handleDelete() {
        String maBenhAn = view.inputMaBenhAnToDelete();
        BenhAn benhAn = service.findByMaBenhAn(maBenhAn);

        if (benhAn == null) {
            view.showMessage("\n[LỖI] Không tìm thấy bệnh án với mã: " + maBenhAn + "\n");
            return;
        }

        if (view.confirmDelete(benhAn)) {
            boolean success = service.delete(maBenhAn);
            if (success) {
                view.showMessage("\n==> Xoá bệnh án thành công!");
                // Hiển thị lại danh sách sau khi xoá
                ArrayList<BenhAn> remaining = service.getAll();
                view.displayMedicalRecords(remaining);
            } else {
                view.showMessage("\n[LỖI] Không thể xoá bệnh án!\n");
            }
        } else {
            view.showMessage("\nĐã huỷ thao tác xoá.\n");
        }
    }

    private void handleDisplayAll() {
        ArrayList<BenhAn> list = service.getAll();
        view.displayMedicalRecords(list);
    }
}
