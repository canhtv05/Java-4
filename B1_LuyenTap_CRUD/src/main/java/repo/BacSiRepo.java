package repo;

import entity.BacSi;
import entity.PhongKham;
import org.hibernate.Session;
import utils.HIbernateConfig;

import java.util.List;

public class BacSiRepo {
    private Session s;

    public BacSiRepo() {
        s = HIbernateConfig.getFACTORY().openSession();;
    }

    public List<BacSi> getAll () {
        return s.createQuery("FROM BacSi ").list();
    }

    public BacSi getOne(Integer id) {
        return s.find(BacSi.class, id);
    }

    public void add(BacSi bs) {
        try {
            s.getTransaction().begin();
            s.save(bs);
            s.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            s.getTransaction().rollback();
        }
    }

    public void update(BacSi bs) {
        try {
            s.getTransaction().begin();
            s.merge(bs);
            s.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            s.getTransaction().rollback();
        }
    }

    public void delete(BacSi bs) {
        try {
            s.getTransaction().begin();
            s.delete(this.getOne(bs.getId()));
            s.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            s.getTransaction().rollback();
        }
    }
}
