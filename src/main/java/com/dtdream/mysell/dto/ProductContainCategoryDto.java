package com.dtdream.mysell.dto;

import com.dtdream.mysell.model.ProductInfo;
import lombok.Data;

/**
 * 商品信息
 * @Author yxiumei
 * @Data 2018/12/8 15:58
 */
@Data
public class ProductContainCategoryDto {

    private ProductInfo productInfo;
    private String categoryName;
}
