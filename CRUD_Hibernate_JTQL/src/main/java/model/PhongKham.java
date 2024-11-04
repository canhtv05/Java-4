package model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Entity
@Table(name = "PhongKham")
public class PhongKham {

    @Id // khoa chinh
    // vi la id tu tang nen them vao
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "ten")
    private String ten;

    @Column(name = "soNha")
    private String soNha;

    /*
     *  sau khi them cai nay xong thi vao file hibernate config
     * */
}
