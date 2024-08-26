package com.githrd.demo_jpa.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import lombok.Data;

@Entity
@Data
public class Gogek {

    @Id
    int gobun;

    String goname;
    String goaddr;
    String gojumin;

    int godam;
}
