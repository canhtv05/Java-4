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
@Table(name = "nhan_vien")
public class NhanVien {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ten")
    private String ten;

    @Column(name = "tuoi")
    private Integer tuoi;

    @Column(name = "luong")
    private Double luong;

    @ManyToOne
    @JoinColumn(name = "id_phong_ban", referencedColumnName = "id")
    private PhongBan phongBan;
}
