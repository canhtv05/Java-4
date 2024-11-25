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
@Table(name = "giao_vien")
public class GiaoVien {
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
    @JoinColumn(name = "id_truong_hoc", referencedColumnName = "id")
    private TruongHoc truongHoc;
}
