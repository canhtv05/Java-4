    package entity;

    import jakarta.persistence.*;
    import lombok.AllArgsConstructor;
    import lombok.Getter;
    import lombok.NoArgsConstructor;
    import lombok.Setter;

    @NoArgsConstructor
    @AllArgsConstructor
    @Getter
    @Setter
    @Entity
    @Table(name = "BacSi")
    public class BacSi {
        @Id
        @GeneratedValue(strategy = GenerationType.IDENTITY)
        private Integer id;

        @Column(name = "ten")
        private String ten;

        @Column(name = "dia_chi")
        private String diaChi;

        @Column(name = "luong")
        private Double luong;

        @ManyToOne
        @JoinColumn(name = "id_phong_kham", referencedColumnName = "id")
        private PhongKham phongKham;
    }
