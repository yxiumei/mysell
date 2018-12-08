package com.dtdream.mysell.mapper;

import com.dtdream.mysell.model.ProductInfo;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ProductInfoMapper {
    int deleteByPrimaryKey(String productId);

    int insert(ProductInfo record);


    ProductInfo selectByPrimaryKey(String productId);

    /**
     * 更新商品
     * @param record
     * @return
     */
    int updateByPrimaryKeySelective(ProductInfo record);

    /**
     * 查询所有上架商品
     * @return
     */
    List<ProductInfo> findUpFrameProduct(Integer status);

    /**
     * 查询商品列表
     * @param productName 商品名
     * @return
     */
    List<ProductInfo> findList(@Param("productName") String productName);
}