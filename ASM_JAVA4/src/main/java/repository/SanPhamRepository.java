package repository;

import entity.KhachHang;
import entity.SanPham;
import entity.SanPhamChiTiet;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repository.base.AbstractRepository;
import util.HibernateConfig;

import java.util.List;

public class SanPhamRepository extends AbstractRepository<SanPham> {
    public SanPhamRepository() {
        super(SanPham.class);
    }

    public List<SanPham> getAllByParam(String param) {
        try (Session s = HibernateConfig.getFACTORY().openSession()) {
            Query<SanPham> query;

            if (param == null || param.trim().isEmpty()) {
                query = s.createQuery("FROM SanPham sp");
            } else {
                query = s.createQuery(
                        "FROM SanPham sp WHERE sp.ten LIKE :param OR sp.ma LIKE :param");
                query.setParameter("param", "%" + param + "%");
            }
            return query.list();
        }
    }

    public SanPham updateTrangThai(Integer id, Integer trangThai) {
        SanPham updatedSanPham = null;
        try (Session session = HibernateConfig.getFACTORY().openSession()) {
            session.beginTransaction();
            SanPham kh = session.get(SanPham.class, id);
            if (kh != null) {
                kh.setTrangThai(trangThai);
                session.update(kh);
                updatedSanPham = kh;
            }
            session.getTransaction().commit();
        } catch (Exception e) {
            e.printStackTrace();

        }
        return updatedSanPham;
    }

}
