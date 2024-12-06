package repository;

import entity.HoaDon;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import repository.base.AbstractRepository;
import util.HibernateConfig;

import java.util.List;

public class HoaDonRepository extends AbstractRepository<HoaDon> {
    public HoaDonRepository() {
        super(HoaDon.class);
    }

    public List<HoaDon> getAllHoaDonByIdNhanVien(Integer id) {
        try (Session s = HibernateConfig.getFACTORY().openSession()) {
            Query<HoaDon> query = s.createQuery("from HoaDon h where h.nhanVien.id = :id");
            query.setParameter("id", id);
            return query.list();
        }
    }

    public HoaDon getOneHoaDonByIdNhanVien(Integer idNV, Integer idHD) {
        try (Session s = HibernateConfig.getFACTORY().openSession()) {
            Query<HoaDon> query = s.createQuery("from HoaDon h where h.nhanVien.id = :idNV and h.id = :idHD");
            query.setParameter("idNV", idNV);
            query.setParameter("idHD", idHD);
            return query.uniqueResult();
        }
    }

    public HoaDon updateTrangThaiHoaDon(Integer id, Integer trangThai) {
        try (Session s = HibernateConfig.getFACTORY().openSession()) {
            Transaction t = s.beginTransaction();
            HoaDon hoaDon = s.get(HoaDon.class, id);
            hoaDon.setTrangThai(trangThai);
            s.update(hoaDon);
            t.commit();
            return hoaDon;
        }
    }
}
