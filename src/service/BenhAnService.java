package service;

import exception.DuplicateMedicalRecordException;
import model.BenhAn;
import repository.BenhAnRepository;
import repository.IBenhAnRepository;

import java.util.ArrayList;

public class BenhAnService implements IBenhAnService {
    private final IBenhAnRepository repository;

    public BenhAnService() {
        this.repository = new BenhAnRepository();
    }

    public BenhAnService(IBenhAnRepository repository) {
        this.repository = repository;
    }

    @Override
    public ArrayList<BenhAn> getAll() {
        return repository.findAll();
    }

    @Override
    public void add(BenhAn benhAn) throws DuplicateMedicalRecordException {
        checkDuplicateMaBenhAn(benhAn.getMaBenhAn());
        repository.add(benhAn);
    }

    @Override
    public boolean delete(String maBenhAn) {
        if (maBenhAn == null || maBenhAn.trim().isEmpty()) {
            return false;
        }
        ArrayList<BenhAn> list = repository.findAll();
        boolean removed = false;
        for (int i = 0; i < list.size(); i++) {
            if (list.get(i).getMaBenhAn().equalsIgnoreCase(maBenhAn.trim())) {
                list.remove(i);
                removed = true;
                break;
            }
        }
        if (removed) {
            repository.saveAll(list);
        }
        return removed;
    }

    @Override
    public BenhAn findByMaBenhAn(String maBenhAn) {
        if (maBenhAn == null || maBenhAn.trim().isEmpty()) {
            return null;
        }
        ArrayList<BenhAn> list = repository.findAll();
        for (BenhAn ba : list) {
            if (ba.getMaBenhAn().equalsIgnoreCase(maBenhAn.trim())) {
                return ba;
            }
        }
        return null;
    }

    @Override
    public boolean existsByMaBenhAn(String maBenhAn) {
        return findByMaBenhAn(maBenhAn) != null;
    }

    @Override
    public void checkDuplicateMaBenhAn(String maBenhAn) throws DuplicateMedicalRecordException {
        if (existsByMaBenhAn(maBenhAn)) {
            throw new DuplicateMedicalRecordException("Bệnh án đã tồn tại.");
        }
    }

    @Override
    public int getNextSoThuTu() {
        ArrayList<BenhAn> list = repository.findAll();
        if (list.isEmpty()) {
            return 1;
        }
        // Quy tắc: số thứ tự mới = số thứ tự cuối trong danh sách + 1
        BenhAn lastRecord = list.get(list.size() - 1);
        return lastRecord.getSoThuTu() + 1;
    }

    @Override
    public String generateSuggestedMaBenhAn() {
        int nextStt = getNextSoThuTu();
        return "BA-" + nextStt;
    }

    @Override
    public String generateSuggestedMaBenhNhan() {
        int nextStt = getNextSoThuTu();
        return "BN-" + nextStt;
    }
}
