package repository;

import entity.SanPham;
import repository.base.AbstractRepository;

public class SanPhamRepository extends AbstractRepository<SanPham> {
    public SanPhamRepository() {
        super(SanPham.class);
    }
}
