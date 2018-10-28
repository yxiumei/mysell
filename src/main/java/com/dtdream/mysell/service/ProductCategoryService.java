package com.dtdream.mysell.service;

import com.dtdream.mysell.model.ProductCategory;

import java.util.List;

public interface ProductCategoryService {

    /**
     * 删除类目
     * @param categoryId
     * @return
     */
    int deleteByPrimaryKey(Integer categoryId);

    /**
     * 新增类目
     * @param record
     * @return
     */
    int insert(ProductCategory record);

    /**
     * 新增类目
     * @param record
     * @return
     */
    int insertSelective(ProductCategory record);

    /**
     * 查询类目
     * @param categoryId
     * @return
     */
    ProductCategory selectByPrimaryKey(Integer categoryId);

    /**
     * 更改类目
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(ProductCategory record);

    /**
     * 更改类目
     * @param record
     * @return
     */
    int updateByPrimaryKey(ProductCategory record);

    /**
     * 通过类目类型查询
     */
    List<ProductCategory> findCategoryByType(List<Integer> list);
}
