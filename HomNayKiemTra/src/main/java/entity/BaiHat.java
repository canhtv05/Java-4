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
@Table(name = "bai_hat")
public class BaiHat {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ten_bai_hat")
    private String ten;

    @Column(name = "gia")
    private Double gia;

    @Column(name = "thoi_luong")
    private Integer thoiLuong;

    @Column(name = "ngay_ra_mat")
    private String ngayRaMat;

    @ManyToOne
    @JoinColumn(name = "ca_si_id", referencedColumnName = "id")
    private CaSi caSi;
}
