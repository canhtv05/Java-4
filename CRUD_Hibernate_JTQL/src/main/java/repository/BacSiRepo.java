package repository;

import model.BacSi;
import org.hibernate.Session;
import util.HibernateConfig;

import java.util.List;

public class BacSiRepo {
    private Session s;


    public BacSiRepo() {
        s = HibernateConfig.getFACTORY().openSession();
    }

    public List<BacSi> getAll() {
        return s.createQuery("FROM BacSi ").list();
    }
}
