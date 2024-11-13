package repository;

import entity.LoaiDieuHoa;
import org.hibernate.Session;
import utils.HibernateConfig;

import java.util.List;

public class LoaiDieuHoaRepo {
    private Session s;

    public LoaiDieuHoaRepo() {
        s = HibernateConfig.getFACTORY().openSession();
    }

    public List<LoaiDieuHoa> getAll() {
        return s.createQuery("FROM LoaiDieuHoa ").list();
    }

    public LoaiDieuHoa getOne(Integer id) {
        return s.find(LoaiDieuHoa.class, id);
    }
}
