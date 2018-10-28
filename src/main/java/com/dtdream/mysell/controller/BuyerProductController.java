package com.dtdream.mysell.controller;

import com.dtdream.mysell.dto.Response;
import com.dtdream.mysell.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * 卖家商品
 * @author 杨秀眉
 */
@RestController
@RequestMapping("/buyer/product")
public class BuyerProductController {

    @Autowired
    private ProductService productService;

    @RequestMapping(
            value = "/list",
            method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE
    )
    public Response list(){
//        List<ProductDto> upFrameProduct = productService.findUpFrameProduct();
//        if (upFrameProduct == null){
//            Response.fail("获取商品失败");
//        }
//        return Response.ok(upFrameProduct);
        return null;
   }
}
