package repository;

import entity.PhongBan;
import org.hibernate.Session;
import util.HibernateConfig;

import java.util.List;
public class PhongBanRepo {
    private Session s;

    public PhongBanRepo() {
        s = HibernateConfig.getFACTORY().openSession();
    }

    public List<PhongBan> getAll() {
        return s.createQuery("FROM PhongBan ").list();
    }

    public PhongBan getOne(Integer id) {
        return s.find(PhongBan.class, id);
    }
}
