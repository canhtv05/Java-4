package enity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "HoaDonChiTiet")

public class HoaDonChiTiet {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ten_san_pham")
    private String tenSanPham;

    @Column(name = "so_luong")
    private Integer soLuong;

    @Column(name = "gia")
    private Double gia;

    @Column(name = "ghi_chu")
    private String ghiChu;

    @ManyToOne
    @JoinColumn(name = "id_hoa_don", referencedColumnName = "id")
    private HoaDon hoaDon;
}
