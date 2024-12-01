package repository;

import entity.HoaDonChiTiet;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repository.base.AbstractRepository;
import util.HibernateConfig;

import java.util.List;

public class HoaDonChiTietRepository extends AbstractRepository<HoaDonChiTiet> {
    public HoaDonChiTietRepository() {
        super(HoaDonChiTiet.class);
    }

    public List<HoaDonChiTiet> getAllByIdHoaDon(Integer idHoaDon) {
        try (Session s = HibernateConfig.getFACTORY().openSession()) {
            Query<HoaDonChiTiet> query = s.createQuery("FROM HoaDonChiTiet hdct where hdct.hoaDon.id = :idHoaDon");
            query.setParameter("idHoaDon", idHoaDon);
            return query.list();
        }
    }
}
