package repository;

import enity.HoaDon;
import org.hibernate.Session;
import util.HibernateConfig;

import java.util.List;

public class HoaDonRepo {
    private Session s;

    public HoaDonRepo() {
        s = HibernateConfig.getFACTORY().openSession();
    }

    public List<HoaDon> getAll() {
        return s.createQuery("FROM HoaDon ").list();
    }

    public HoaDon getOne(Integer id) {
        return s.find(HoaDon.class, id);
    }
}
