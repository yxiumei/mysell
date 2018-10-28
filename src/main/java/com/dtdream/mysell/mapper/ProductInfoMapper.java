package com.dtdream.mysell.mapper;

import com.dtdream.mysell.model.ProductInfo;

import java.util.List;

public interface ProductInfoMapper {
    int deleteByPrimaryKey(String productId);

    int insert(ProductInfo record);

    int insertSelective(ProductInfo record);

    ProductInfo selectByPrimaryKey(String productId);

    int updateByPrimaryKeySelective(ProductInfo record);

    int updateByPrimaryKey(ProductInfo record);

    /**
     * 查询所有上架商品
     * @return
     */
    List<ProductInfo> findUpFrameProduct(Integer status);
}