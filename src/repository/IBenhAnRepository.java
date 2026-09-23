package repository;

import model.BenhAn;
import java.util.ArrayList;

public interface IBenhAnRepository {

    ArrayList<BenhAn> findAll();

    void saveAll(ArrayList<BenhAn> list);

    void add(BenhAn benhAn);
}
