package com.githrd.demo_transaction.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.githrd.demo_transaction.vo.ProductVo;

@Mapper
public interface ProductOutMapper {

	List<ProductVo> 	selectList();
	ProductVo			selectOne(int idx);
	ProductVo			selectOne(String name);
	int					insert(ProductVo vo);
	int					update(ProductVo vo);
	int					delete(int idx);
}
