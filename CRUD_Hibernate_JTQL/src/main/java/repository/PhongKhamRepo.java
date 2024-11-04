package repository;

import model.PhongKham;
import org.hibernate.Session;
import util.HibernateConfig;

import java.util.List;

public class PhongKhamRepo {
    private Session s;

    public PhongKhamRepo() {
        s = HibernateConfig.getFACTORY().openSession();
    }

    public List<PhongKham> getAll() {
        // return ve list
        return s.createQuery("FROM PhongKham ").list();
    }

    public PhongKham getOne(Integer id) {
        return s.find(PhongKham.class, id);
    }

    public void add(PhongKham phongKham) {
        try {
            s.getTransaction().begin();
            s.save(phongKham);
            s.getTransaction().commit();
        } catch (Exception e) {
            s.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public void update(PhongKham phongKham) {
        try {
            s.getTransaction().begin();
            s.merge(phongKham);
            s.getTransaction().commit();
        } catch (Exception e) {
            s.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public void delete(PhongKham phongKham) {
        try {
            s.getTransaction().begin();
            s.delete(getOne(phongKham.getId()));
            s.getTransaction().commit();
        } catch (Exception e) {
            s.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}
