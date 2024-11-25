package repository;

import entity.GiaoVien;
import org.hibernate.Session;
import util.HibernateConfig;

import java.util.List;

public class GiaoVienRepository {
    private Session s;

    public GiaoVienRepository() {
        s = HibernateConfig.getFACTORY().openSession();
    }

    public List<GiaoVien> getAll() {
        return s.createQuery("FROM GiaoVien ").list();
    }

    public GiaoVien getOne(Integer id) {
        return s.find(GiaoVien.class, id);
    }

    public void add(GiaoVien gv) {
        try {
            s.getTransaction().begin();
            s.save(gv);
            s.getTransaction().commit();
        }catch (Exception e) {
            s.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public void delete(Integer id) {
        try {
            s.getTransaction().begin();
            s.delete(this.getOne(id));
            s.getTransaction().commit();
        }catch (Exception e) {
            s.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public void update(GiaoVien gv) {
        try {
            s.getTransaction().begin();
            s.merge(gv);
            s.getTransaction().commit();
        }catch (Exception e) {
            s.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}
