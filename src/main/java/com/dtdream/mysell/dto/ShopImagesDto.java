package com.dtdream.mysell.dto;

import com.dtdream.mysell.model.Shop;
import lombok.Data;

/**
 * @Author yxiumei
 * @Data 2018/12/12 22:06
 */
@Data
public class ShopImagesDto {

    private Shop shop;
    /**
     * 公告
     */
    private String bulletin;
    /**
     * 店铺信息
     */
    private String infos;
    /** 店铺图片 */
    private String shopImg1;
    private String shopImg2;
    private String shopImg3;
    private String shopImg4;

}
