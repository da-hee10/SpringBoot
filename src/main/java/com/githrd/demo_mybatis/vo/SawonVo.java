package com.githrd.demo_mybatis.vo;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
@Alias("sawon")
public class SawonVo {

    int sabun;
    int deptno;
    int samgr;
    int sapay;

    String saname;
    String sajob;
    String sasex;
    String sahire;

}
