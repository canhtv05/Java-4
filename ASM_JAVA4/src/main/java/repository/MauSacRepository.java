package repository;

import entity.MauSac;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repository.base.AbstractRepository;
import util.HibernateConfig;

import java.util.List;

public class MauSacRepository extends AbstractRepository<MauSac> {

    public MauSacRepository() {
        super(MauSac.class);
    }

    public List<MauSac> getAllWhenIdEqualsTo1() {
        try(Session s = HibernateConfig.getFACTORY().openSession()) {
            Query<MauSac> query = s.createQuery("FROM MauSac WHERE trangThai = 1");
            return query.list();
        }
    }
}
