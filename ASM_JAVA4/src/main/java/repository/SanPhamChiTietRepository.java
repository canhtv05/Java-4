package repository;

import entity.SanPhamChiTiet;
import org.hibernate.Session;
import org.hibernate.query.Query;
import repository.base.AbstractRepository;
import util.HibernateConfig;

import java.util.List;

public class SanPhamChiTietRepository extends AbstractRepository<SanPhamChiTiet> {
    public SanPhamChiTietRepository() {
        super(SanPhamChiTiet.class);
    }

    public List<SanPhamChiTiet> getAllByParam(String param) {
        try (Session s = HibernateConfig.getFACTORY().openSession()) {
            Query<SanPhamChiTiet> query;

            if (param == null || param.trim().isEmpty()) {
                query = s.createQuery("FROM SanPhamChiTiet spct");
            } else {
                query = s.createQuery(
                        "FROM SanPhamChiTiet spct WHERE spct.sanPham.ma LIKE :param OR spct.sanPham.ten LIKE :param " +
                                "OR spct.mauSac.ten LIKE :param OR spct.kichThuoc.ten LIKE :param");
                query.setParameter("param", "%" + param + "%");
            }
            return query.list();
        }
    }

}