package com.dtdream.mysell.controller;

import com.dtdream.mysell.dto.ProductDto;
import com.dtdream.mysell.dto.Response;
import com.dtdream.mysell.service.OrderService;
import com.dtdream.mysell.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * 卖家商品
 * @author yxiumei
 */
@Slf4j
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private OrderService orderService;

    @RequestMapping(
            value = "/list",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response<List<ProductDto>> list(){
        Response<List<ProductDto>> upFrameProduct1 = productService.findUpFrameProduct();
        return upFrameProduct1;
   }

}
