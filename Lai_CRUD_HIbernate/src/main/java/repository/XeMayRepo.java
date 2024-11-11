package repository;

import entity.XeMay;
import org.hibernate.Session;
import util.HibernateConfig;

import java.util.List;

public class XeMayRepo {
    private Session s;

    public XeMayRepo() {
        s = HibernateConfig.getFACTORY().openSession();
    }

    public List<XeMay> getAll() {
        return s.createQuery("FROM XeMay ").list();
    }

    public XeMay findOne(Integer id) {
        return s.find(XeMay.class, id);
    }

    public void add(XeMay xm) {
        try {
            s.getTransaction().begin();
            s.save(xm);
            s.getTransaction().commit();
        } catch (Exception e) {
            s.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public void update(XeMay xm) {
        try {
            s.getTransaction().begin();
            s.merge(xm);
            s.getTransaction().commit();
        } catch (Exception e) {
            s.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public void delete(XeMay xm) {
        try {
            s.getTransaction().begin();
            s.delete(findOne(xm.getId()));
            s.getTransaction().commit();
        } catch (Exception e) {
            s.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}
