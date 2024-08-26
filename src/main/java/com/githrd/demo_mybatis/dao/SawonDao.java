package com.githrd.demo_mybatis.dao;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;

import com.githrd.demo_mybatis.vo.SawonVo;

@Mapper
public interface SawonDao {

    List<SawonVo> selectList(Map<String,Object> map);

}
