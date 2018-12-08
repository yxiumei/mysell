package com.dtdream.mysell.dto;

import com.dtdream.mysell.enums.ProductEnum;
import com.dtdream.mysell.model.ProductInfo;
import com.dtdream.mysell.utils.EnumUtils;
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

    private String productStatus;

    public String getProductStatus() {
        Integer status = productInfo.getStatus();
        ProductEnum byCode = EnumUtils.getByCode(status, ProductEnum.class);
        return byCode.getMsg();
    }
}
