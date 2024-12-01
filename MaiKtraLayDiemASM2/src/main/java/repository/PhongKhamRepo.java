package repository;

import entity.PhongKham;
import org.hibernate.Session;
import util.HibernateConfig;

import java.util.List;

public class PhongKhamRepo {
    private Session s;

    public PhongKhamRepo() {
        s = HibernateConfig.getFACTORY().openSession();
    }

    public List<PhongKham> getAll(Integer pageNo, Integer pageSize) {
        return s.createQuery("FROM PhongKham ").setFirstResult((pageNo - 1) * pageSize).setMaxResults(pageSize).getResultList();
    }

    public Integer getTotalCount() {
        Long count = (Long)s.createQuery("SELECT count (pk) from PhongKham pk").uniqueResult();
        return count != null ? count.intValue() : 0;
    }
}
