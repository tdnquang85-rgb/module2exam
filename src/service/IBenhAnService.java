package service;

import exception.DuplicateMedicalRecordException;
import model.BenhAn;

import java.util.ArrayList;

public interface IBenhAnService {

    ArrayList<BenhAn> getAll();

    void add(BenhAn benhAn) throws DuplicateMedicalRecordException;

    boolean delete(String maBenhAn);

    BenhAn findByMaBenhAn(String maBenhAn);

    boolean existsByMaBenhAn(String maBenhAn);

    void checkDuplicateMaBenhAn(String maBenhAn) throws DuplicateMedicalRecordException;

    int getNextSoThuTu();

    String generateSuggestedMaBenhAn();

    String generateSuggestedMaBenhNhan();
}
