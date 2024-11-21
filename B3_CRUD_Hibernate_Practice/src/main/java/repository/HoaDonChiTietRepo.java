package repository;

import enity.HoaDon;
import enity.HoaDonChiTiet;
import org.hibernate.Session;
import util.HibernateConfig;

import java.util.List;

public class HoaDonChiTietRepo {
    private Session s;

    public HoaDonChiTietRepo() {
        s = HibernateConfig.getFACTORY().openSession();
    }

    public List<HoaDonChiTiet> getAll() {
        return s.createQuery("FROM HoaDonChiTiet ").list();
    }

    public HoaDonChiTiet getOne(Integer id) {
        return s.find(HoaDonChiTiet.class, id);
    }

    public void add(HoaDonChiTiet hd) {
        try {
            s.getTransaction().begin();
            s.save(hd);
            s.getTransaction().commit();
        }catch (Exception e) {
            s.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public void update(HoaDonChiTiet hd) {
        try {
            s.getTransaction().begin();
            s.merge(hd);
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
}
