package repository;

import model.BacSi;
import model.PhongKham;
import org.hibernate.Session;
import util.HibernateConfig;

import java.util.List;

public class BacSiRepo {
    private Session s;


    public BacSiRepo() {
        s = HibernateConfig.getFACTORY().openSession();
    }

    public BacSi getOne(Integer id) {
        return s.find(BacSi.class, id);
    }

    public List<BacSi> getAll() {
        return s.createQuery("FROM BacSi ").list();
    }

    public void delete(BacSi bs) {
        try {
            s.getTransaction().begin();
            s.delete(this.getOne(bs.getId()));
            s.getTransaction().commit();
        } catch (Exception e) {
            s.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public void add(BacSi bs) {
        try {
            s.getTransaction().begin();
            s.save(bs);
            s.getTransaction().commit();
        } catch (Exception e) {
            s.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public void update(BacSi bs) {
        try {
            s.getTransaction().begin();
            s.merge(bs);
            s.getTransaction().commit();
        } catch (Exception e) {
            s.getTransaction().rollback();
            e.printStackTrace();
        }
    }
}
