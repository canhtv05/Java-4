package repository;

import entity.DanhMuc;
import org.hibernate.Session;
import util.HibernateConfig;

import java.util.List;

public class DanhMucRepo {
    private Session s;

    public DanhMucRepo() {
        try {
            s = HibernateConfig.getFACTORY().openSession();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public List<DanhMuc> getAll() {
        return s.createQuery("FROM DanhMuc ").list();
    }

    public DanhMuc getOne(Integer id) {
        return s.find(DanhMuc.class, id);
    }

    public void add(DanhMuc danhMuc) {
        try {
            s.getTransaction().begin();
            s.save(danhMuc);
            s.getTransaction().commit();
        }catch (Exception e) {
            s.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public void update(DanhMuc danhMuc) {
        try {
            s.getTransaction().begin();
            s.merge(danhMuc);
            s.getTransaction().commit();
        }catch (Exception e) {
            s.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public void delete(DanhMuc danhMuc) {
        try {
            s.getTransaction().begin();
            s.delete(this.getOne(danhMuc.getId()));
            s.getTransaction().commit();
        }catch (Exception e) {
            s.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}
