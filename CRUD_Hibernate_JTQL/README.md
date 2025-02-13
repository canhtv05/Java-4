# Lưu ý: cài đặt đủ SQL server, Hibernate, JSTL (forEach),

# Template HibernateConfigLombok

```java
#if(${PACKAGE_NAME}&&${PACKAGE_NAME}!="")package ${PACKAGE_NAME};#end

import org.hibernate.SessionFactory;
import org.hibernate.boot.registry.StandardServiceRegistryBuilder;
import org.hibernate.cfg.Configuration;
import org.hibernate.cfg.Environment;
import org.hibernate.service.ServiceRegistry;

import java.util.Properties;

public class $ {
    Class_Name
} {
private static final SessionFactory FACTORY;

static {
        Configuration conf=new Configuration();

        Properties properties=new Properties();
        properties.put(Environment.DIALECT,"org.hibernate.dialect.SQLServer2016Dialect");
        properties.put(Environment.DRIVER,"com.microsoft.sqlserver.jdbc.SQLServerDriver");
        properties.put(Environment.URL,"jdbc:sqlserver://localhost:1433;databaseName=${DB_NAME};encrypt=true;trustServerCertificate=true;");
        properties.put(Environment.USER,"${DB_USER}");
        properties.put(Environment.PASS,"${DB_PASS}");
        properties.put(Environment.SHOW_SQL,"true");

        conf.setProperties(properties);
        ServiceRegistry registry=new StandardServiceRegistryBuilder()
        .applySettings(conf.getProperties()).build();
        FACTORY=conf.buildSessionFactory(registry);
        }

public static SessionFactory getFACTORY(){
        return FACTORY;
        }

public static void main(String[]args){
        System.out.println(getFACTORY());
        }
        }

```

---

# DB

```sql
CREATE
DATABASE CRUD_Hibernate;
GO
USE CRUD_Hibernate;
GO

CREATE TABLE PhongKham
(
    id    INT PRIMARY KEY IDENTITY(1, 1),
    ten   VARCHAR(50),
    soNha INT
);

INSERT INTO dbo.PhongKham
(ten,
 soNha)
VALUES ('Phong kham1', -- ten - varchar(50)
        1 -- soNha - int
       ),
       ('Phong kham2', -- ten - varchar(50)
        2 -- soNha - int
       );

`Need to add this code to join the table`

CREATE TABLE Bacsi
(
    id INT PRIMARY KEY IDENTITY(1, 1),
    ten VARCHAR(50),
    dia_chi VARCHAR(50),
    luong FLOAT,
    id_phong_kham INT,
    FOREIGN KEY (id_phong_kham) REFERENCES dbo.PhongKham (id)
);

INSERT INTO dbo.Bacsi
(
    ten,
    dia_chi,
    luong,
    id_phong_kham
)
VALUES
(   'van canh', -- ten - varchar(50)
    'diachi',   -- dia_chi - varchar(50)
    111,        -- luong - float
    6           -- id_phong_kham - int
    ),
(   'van canh', -- ten - varchar(50)
    'diachi',   -- dia_chi - varchar(50)
    5,          -- luong - float
    7           -- id_phong_kham - int
),
(   'van canh', -- ten - varchar(50)
    'diachi',   -- dia_chi - varchar(50)
    353,        -- luong - float
    8           -- id_phong_kham - int
);
```
