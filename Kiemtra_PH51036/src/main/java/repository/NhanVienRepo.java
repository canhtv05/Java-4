package repository;

import entity.NhanVien;
import org.hibernate.Session;
import util.HibernateConfig;

import java.util.List;

public class NhanVienRepo {
    private Session s;

    public NhanVienRepo() {
        s = HibernateConfig.getFACTORY().openSession();
    }

    public List<NhanVien> getAll() {
        return s.createQuery("FROM NhanVien ").list();
    }

    public NhanVien getOne(Integer id) {
        return s.find(NhanVien.class, id);
    }

    public List<NhanVien> paging(Integer pageNo, Integer pageSize) {
        return s.createQuery("FROM NhanVien ").setFirstResult(pageNo).setMaxResults(pageSize).list();
    }

    public Integer totalCount() {
        Long count = (Long)s.createQuery("FROM NhanVien ").uniqueResult();
        return count != null ? count.intValue() : 0;
    }

    public void add(NhanVien nv) {
        try {
            s.getTransaction().begin();
            s.save(nv);
            s.getTransaction().commit();
        } catch (Exception e) {
            s.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public void update(NhanVien nv) {
        try {
            s.getTransaction().begin();
            s.merge(nv);
            s.getTransaction().commit();
        } catch (Exception e) {
            s.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public void delete(Integer id) {
        try {
            s.getTransaction().begin();
            s.delete(this.getOne(id));
            s.getTransaction().commit();
        } catch (Exception e) {
            s.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}
