package com.githrd.demo_mybatis.vo;

import org.apache.ibatis.type.Alias;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

//@Getter + @Setter   -> @Data
@Data
@NoArgsConstructor  // Default Constructor
@AllArgsConstructor // All Property 넣어준 Overload된 생성자
@Alias("dept")
public class DeptVo {

    int     deptno;
    String  dname;
    String  loc;
}
