package com.githrd.demo_transaction.dao;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.githrd.demo_transaction.vo.ProductVo;

@Mapper
public interface ProductRemainMapper {

	List<ProductVo> 	selectList();
	ProductVo			selectOneFromIdx(int idx);
	ProductVo			selectOneFromName(String name);
	int					insert(ProductVo vo);
	int					update(ProductVo vo);
	int					delete(int idx);
}
