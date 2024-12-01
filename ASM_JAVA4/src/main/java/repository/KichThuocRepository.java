package repository;

import entity.KichThuoc;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repository.base.AbstractRepository;
import util.HibernateConfig;

import java.util.List;

public class KichThuocRepository extends AbstractRepository<KichThuoc> {
    public KichThuocRepository() {
        super(KichThuoc.class);
    }

    public List<KichThuoc> getAllWhenIdEqualsTo1() {
        try(Session s = HibernateConfig.getFACTORY().openSession()) {
            Query<KichThuoc> query = s.createQuery("FROM KichThuoc WHERE trangThai = 1");
            return query.list();
        }
    }
}
