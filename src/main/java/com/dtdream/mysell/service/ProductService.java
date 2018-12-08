package com.dtdream.mysell.service;

import com.dtdream.mysell.dto.CartDto;
import com.dtdream.mysell.dto.ProductContainCategoryDto;
import com.dtdream.mysell.dto.ProductDto;
import com.dtdream.mysell.dto.Response;
import com.dtdream.mysell.model.ProductInfo;
import com.github.pagehelper.PageInfo;

import java.util.List;

/**
 * 商品
 * @author yxiumei
 */
public interface ProductService {

    /**
     * 查询所有上架商品
     */
    Response<List<ProductDto>> findUpFrameProduct();

    /**
     * 加库存
     * @param cartDtoList
     * @return
     */
    Response<Boolean> increaseStock(List<CartDto> cartDtoList);

    /**
     * 扣库存
     * @param cartDtoList
     * @return
     */
    Response<Boolean> decreaseStock(List<CartDto> cartDtoList);

    /**
     * 获取商品列表
     * @param pageNo 当前页
     * @param pageSize 页大小
     * @param key 搜索关键字(商品名)
     * @return
     */
    Response<PageInfo<ProductContainCategoryDto>> productList(Integer pageNo, Integer pageSize, String key);

    /**
     * 商品上架
     * @param productId 商品id
     * @return
     */
    Response<Boolean> productUpFrom(String productId);

    /**
     * 商品下架
     * @param productId 商品下架
     * @return
     */
    Response<Boolean> productDownFrom(String productId);

    /**
     * 保存商品
     * @param productInfo
     * @return
     */
    Response<Boolean> save(ProductInfo productInfo);

    /**
     * 编辑商品
     * @param productInfo
     * @return
     */
    Response<Boolean> update(ProductInfo productInfo);

    /**
     * 通过id查询商品
     * @param productId
     * @return
     */
    Response<ProductInfo> findOne(String productId);
}
