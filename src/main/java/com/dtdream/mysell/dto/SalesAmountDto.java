package com.dtdream.mysell.dto;

import lombok.Data;

/**
 * 销售额
 * @Author yxiumei
 * @Data 2019/3/8 18:18
 */
@Data
public class SalesAmountDto {

    /**
     * 总销售额
     */
    private Double totalAmout;

    /**
     * 当月销售额
     */
    private Double thisMonth;
}
