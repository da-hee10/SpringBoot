package com.githrd.demo_mybatis.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Select;

import com.githrd.demo_mybatis.vo.DeptVo;

@Mapper
public interface DeptDao {

    // XML의 Mapper정보를 이용하려면
    // 1.DaptDao == DeptDao.xml
    // 2.namespace = "Mapper정보"
    //   namespace = "com.githrd.demo_mybatis.dao.DeptDao"
    // 3.mapper id = "메소드명"
    //          id = "selectList"

    //@Select("select * from dept")
    List<DeptVo> selectList();

    @Select("select * from dept where deptno=#{deptno}")
    List<DeptVo> selectListFromDeptNo(int deptno);

    List<DeptVo> selectListFromLoc(String loc);
}
