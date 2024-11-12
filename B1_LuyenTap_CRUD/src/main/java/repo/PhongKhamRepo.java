package repo;

import entity.PhongKham;
import org.hibernate.Session;
import utils.HIbernateConfig;

import java.util.List;

public class PhongKhamRepo {
    private Session s;

    public PhongKhamRepo() {
        s = HIbernateConfig.getFACTORY().openSession();;
    }

    public List<PhongKham> getAll () {
        return s.createQuery("FROM PhongKham ").list();
    }

    public PhongKham getOne(Integer id) {
        return s.find(PhongKham.class, id);
    }

    public void add(PhongKham pk) {
        try {
            s.getTransaction().begin();
            s.save(pk);
            s.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            s.getTransaction().rollback();
        }
    }

    public void update(PhongKham pk) {
        try {
            s.getTransaction().begin();
            s.merge(pk);
            s.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            s.getTransaction().rollback();
        }
    }

    public void delete(PhongKham pk) {
        try {
            s.getTransaction().begin();
            s.delete(this.getOne(pk.getId()));
            s.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
            s.getTransaction().rollback();
        }
    }
}
