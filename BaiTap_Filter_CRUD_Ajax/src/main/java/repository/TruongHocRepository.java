package repository;

import entity.TruongHoc;
import org.hibernate.Session;
import util.HibernateConfig;

import java.util.List;

public class TruongHocRepository {
    private Session s;

    public TruongHocRepository() {
        s = HibernateConfig.getFACTORY().openSession();
    }

    public List<TruongHoc> getAll() {
        return s.createQuery("FROM TruongHoc ").list();
    }

    public TruongHoc getOne(Integer id) {
        return s.find(TruongHoc.class, id);
    }
}
