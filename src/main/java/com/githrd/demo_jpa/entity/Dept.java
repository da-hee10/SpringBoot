package com.githrd.demo_jpa.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.Table;
import lombok.Data;

@Data   // @Getter + @Setter + @toString
@Entity
@Table(name = "dept")   // Entity명과 Table명과 동일하면 생략 가능
public class Dept {

    @Id
    //@GeneratedValue(strategy = GenerationType.IDENTITY)
    Integer deptno;

    // 컬럼명/제약조건
    @Column(name = "dname", nullable = false)
    String  dname;

    @Column
    String  loc;
}
