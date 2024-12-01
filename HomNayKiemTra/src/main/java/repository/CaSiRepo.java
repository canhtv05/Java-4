package repository;

import entity.CaSi;
import org.hibernate.Session;
import util.HibernateConfig;

import java.util.List;

public class CaSiRepo {
    private Session s;

    public CaSiRepo() {
        s = HibernateConfig.getFACTORY().openSession();
    }

    public List<CaSi> getAll() {
        return s.createQuery("FROM CaSi ").getResultList();
    }

    public CaSi getOne(Integer id) {
        return s.find(CaSi.class, id);
    }
}
