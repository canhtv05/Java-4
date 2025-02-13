package repository;

import entity.DieuHoa;
import org.hibernate.Session;
import utils.HibernateConfig;

import java.util.List;

public class DieuHoaRepo {
    private Session s;

    public DieuHoaRepo() {
        s = HibernateConfig.getFACTORY().openSession();
    }

    public DieuHoa getOne(Integer id) {
        return s.find(DieuHoa.class, id);
    }

    public List<DieuHoa> getAll() {
        return s.createQuery("FROM DieuHoa ").list();
    }

    public List<DieuHoa> pagination(Integer pageNo, Integer pageSize) {
        return s.createQuery("FROM DieuHoa ").setFirstResult(pageNo).setMaxResults(pageSize).list();
    }

        public Integer getTotalCount() {
            Long count =(Long) s.createQuery("SELECT COUNT (d) from DieuHoa d").uniqueResult();

            return count != null ? count.intValue() : 0;
        }

    public void add(DieuHoa dh) {
        try {
            s.getTransaction().begin();
            s.save(dh);
            s.getTransaction().commit();
        } catch (Exception e) {
            s.getTransaction().rollback();
            e.printStackTrace();
        }
    }

    public void update(DieuHoa dh) {
        try {
            s.getTransaction().begin();
            s.merge(dh);
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
