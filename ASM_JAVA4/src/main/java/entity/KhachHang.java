package entity;

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
@Table(name = "KhachHang")
public class KhachHang {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ten", unique = true, nullable = false)
    private String ten;

    @Column(name = "sdt", nullable = false)
    private String sdt;

    @Column(name = "ma_kh", nullable = false)
    private String maKH;

    @Column(name = "trang_thai")
    private Integer trangThai;
}
