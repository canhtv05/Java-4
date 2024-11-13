package repository;

import model.PhongKham;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.query.Query;
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

    public List<PhongKham> search(String ten) {
        Query query = s.createQuery("FROM PhongKham WHERE ten like :ten");
        query.setParameter("ten", "%" + ten + "%");
        return (List<PhongKham>) query.list();
    }

    public List<PhongKham> paging(Integer pageNo, Integer pageSize) {
        if (pageNo < 1) pageNo = 1;
        Query query = s.createQuery("FROM PhongKham ");
        query.setFirstResult((pageNo - 1) * pageSize);
        query.setMaxResults(pageSize);
        return (List<PhongKham>) query.list();
    }

    public List<PhongKham> limitPage(Integer currentPage) {
        Criteria criteria = s.createCriteria(PhongKham.class);
        criteria.setFirstResult(currentPage * 5);
        criteria.setMaxResults(5);
        return criteria.list();
    }
}
