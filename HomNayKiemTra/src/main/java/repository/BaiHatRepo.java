package repository;

import entity.BaiHat;
import entity.CaSi;
import org.hibernate.Session;
import util.HibernateConfig;

import java.util.List;

public class BaiHatRepo {
    private Session s;

    public BaiHatRepo() {
        s = HibernateConfig.getFACTORY().openSession();
    }

    public List<BaiHat> getAll() {
        return s.createQuery("FROM BaiHat ").getResultList();
    }

    public List<BaiHat> paging(Integer pageNo, Integer pageSize) {
        return s.createQuery("FROM BaiHat ").setFirstResult((pageNo - 1) * pageSize).setMaxResults(pageSize).getResultList();
    }

    public Integer total() {
        Long count =(Long) s.createQuery("SELECT count(bh) from BaiHat bh").uniqueResult();
        return count != null ? count.intValue() : 0;
    }

    public BaiHat getOne(Integer id) {
        return s.find(BaiHat.class, id);
    }

    public void add(BaiHat bh) {
        try {
            s.getTransaction().begin();
            s.save(bh);
            s.getTransaction().commit();
        }catch (Exception e) {
            e.printStackTrace();
            s.getTransaction().rollback();
        }
    }
}
