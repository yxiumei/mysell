package com.dtdream.mysell.mapper;

import com.dtdream.mysell.model.ProductCategory;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductCategoryMapper {
    int deleteByPrimaryKey(Integer categoryId);

    int insert(ProductCategory record);

    int insertSelective(ProductCategory record);

    ProductCategory selectByPrimaryKey(Integer categoryId);

    int updateByPrimaryKeySelective(ProductCategory record);

    int updateByPrimaryKey(ProductCategory record);

    /**
     * 通过类目类型查询
     */
    List<ProductCategory> findCategoryByType(@Param("categoryType") List<Integer> list);
}