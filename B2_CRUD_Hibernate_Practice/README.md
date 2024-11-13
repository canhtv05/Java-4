# DATABASE

```sql
CREATE
DATABASE B2_CRUD_Hibernate_Practice;
GO
USE B2_CRUD_Hibernate_Practice;
GO

CREATE TABLE LoaiDieuHoa
(
    id  INT PRIMARY KEY IDENTITY(1, 1),
    ten VARCHAR(50)
);

GO

CREATE TABLE DieuHoa
(
    id          INT PRIMARY KEY IDENTITY(1, 1),
    ten         VARCHAR(50),
    gia         FLOAT,
    so_luong    INT,
    id_dieu_hoa INT,
    FOREIGN KEY (id_dieu_hoa) REFERENCES dbo.LoaiDieuHoa (id)
);

INSERT INTO dbo.LoaiDieuHoa
(ten)
VALUES ('suzuki' -- ten - varchar(50)
       ),
       ('yamaha' -- ten - varchar(50)
       ),
       ('khong biet' -- ten - varchar(50)
       );

INSERT INTO dbo.DieuHoa
(ten,
 gia,
 so_luong,
 id_dieu_hoa)
VALUES ('100k thoi', -- ten - varchar(50)
        999, -- gia - float
        199, -- so_luong - int
        1 -- id_dieu_hoa - int
       ),
       ('999k thoi', -- ten - varchar(50)
        100, -- gia - float
        1, -- so_luong - int
        2 -- id_dieu_hoa - int
       ),
       ('dung hoi gia', -- ten - varchar(50)
        234, -- gia - float
        999, -- so_luong - int
        3 -- id_dieu_hoa - int
       );

SELECT dh.ten, dh.gia, dh.so_luong, ldh.ten
FROM dbo.DieuHoa dh
         JOIN dbo.LoaiDieuHoa ldh
              ON ldh.id = dh.id
```