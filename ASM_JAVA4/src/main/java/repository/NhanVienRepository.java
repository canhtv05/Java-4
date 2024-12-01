package repository;

import entity.NhanVien;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.query.Query;
import repository.base.AbstractRepository;
import util.HibernateConfig;

public class NhanVienRepository extends AbstractRepository<NhanVien> {
    public NhanVienRepository() {
        super(NhanVien.class);
    }

    public NhanVien login(String username, String password) {
        Transaction transaction = null;
        NhanVien nhanVien = null;
        Session session = null;

        try {
            session = HibernateConfig.getFACTORY().openSession();
            transaction = session.beginTransaction();

            Query<NhanVien> query = session.createQuery("FROM NhanVien WHERE tenDangNhap = :tenDangNhap AND matKhau = :matKhau");
            query.setParameter("tenDangNhap", username);
            query.setParameter("matKhau", password);

            nhanVien = query.uniqueResult();

            transaction.commit();
        } catch (Exception e) {
            if (transaction != null) {
                transaction.rollback();
            }
            e.printStackTrace();
        } finally {
            if (session != null && session.isOpen()) {
                session.close();
            }
        }

        return nhanVien;
    }

}
