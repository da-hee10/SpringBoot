package com.githrd.demo_jpa.entity;

import java.util.List;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import lombok.Data;

@Entity
@Data
public class Sawon {

    @Id
    int sabun;

    String saname;
    String sasex;

    String sajob;
    String sahire;

    @Column(nullable = true)
    Integer samgr;

    int sapay;

    // 참조만 하도록 설정
    @Column(insertable = false, updatable = false)
    int deptno;

    @OneToOne
    @JoinColumn(name = "deptno", referencedColumnName = "deptno")
    Dept dept;

    @OneToMany
    @JoinColumn(name= "godam", referencedColumnName = "sabun")
    List<Gogek> goList;
}
