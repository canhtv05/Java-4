package repository;

import entity.KhachHang;
import entity.SanPhamChiTiet;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repository.base.AbstractRepository;
import util.HibernateConfig;

import java.util.List;

public class KhachHangRepository extends AbstractRepository<KhachHang> {
    public KhachHangRepository() {
        super(KhachHang.class);
    }

    public List<KhachHang> getAllByParam(String param) {
        try (Session s = HibernateConfig.getFACTORY().openSession()) {
            Query<KhachHang> query;

            if (param == null || param.trim().isEmpty()) {
                query = s.createQuery("FROM KhachHang spct");
            } else {
                query = s.createQuery(
                        "FROM KhachHang kh WHERE kh.ten LIKE :param OR kh.sdt LIKE :param");
                query.setParameter("param", "%" + param + "%");
            }
            return query.list();
        }
    }

    public KhachHang updateTrangThai(Integer id, Integer trangThai) {
        KhachHang updatedKhachHang = null;
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            session.beginTransaction();
            KhachHang kh = session.get(KhachHang.class, id);
            if (kh != null) {
                kh.setTrangThai(trangThai);
                session.update(kh);
                updatedKhachHang = kh;
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();
        }
        return updatedKhachHang;
    }

}
