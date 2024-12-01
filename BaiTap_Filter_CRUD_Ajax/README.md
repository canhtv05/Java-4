#DB

```sql
CREATE DATABASE FILTER_CRUD_AJAX
GO
USE FILTER_CRUD_AJAX
GO
CREATE TABLE truong_hoc (
	id INT IDENTITY(1,1) PRIMARY KEY,
	ten varchar(50)
)
GO
INSERT INTO truong_hoc
VALUES
	('CD FPT Polytechnic'),
	('DH FPT'),
	('DH Thang Long')
GO
CREATE TABLE giao_vien (
	id INT IDENTITY(1,1) PRIMARY KEY,
	ten varchar(50),
	tuoi int,
	luong float,
	id_truong_hoc int,
	FOREIGN KEY(id_truong_hoc) REFERENCES truong_hoc(id)
)
GO
INSERT INTO giao_vien
VALUES 
	('Nguyen Van A', 19, 58.3, 1),
	('Nguyen Thi B', 29, 18.3, 2),
	('Tran Van C', 48, 199, 3),
	('Nguyen Canh D', 99, 44.3, 3)
GO

SELECT  *
FROM dbo.giao_vien gv
JOIN dbo.truong_hoc th
ON th.id = gv.id_truong_hoc
```