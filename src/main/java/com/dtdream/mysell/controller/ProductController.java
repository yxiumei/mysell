package com.dtdream.mysell.controller;

import com.dtdream.mysell.dto.ProductContainCategoryDto;
import com.dtdream.mysell.dto.Response;
import com.dtdream.mysell.model.ProductCategory;
import com.dtdream.mysell.model.ProductInfo;
import com.dtdream.mysell.service.ProductCategoryService;
import com.dtdream.mysell.service.ProductService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.List;
import java.util.Map;

/**
 * C端商品管理
 * @Author yxiumei
 * @Data 2018/12/8 15:22
 */
@Controller
@Slf4j
@RequestMapping("/admin/product")
public class ProductController {

    @Autowired
    private ProductService productService;
    @Autowired
    private ProductCategoryService productCategoryService;
    /**
     * 获取商品列表
     * @param page
     * @param pageSize
     * @param map
     * @return
     */
    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                             @RequestParam(required = false) String key,
                             Map<String,Object> map){
        Response<PageInfo<ProductContainCategoryDto>> response = productService.productList(page, pageSize, key);
        if (!response.isSuccess()){
            log.error("OP[]ProductController[]list[]get product fail");
            map.put("msg","获取商品列表失败");
            return new ModelAndView("/error",map);
        }
        map.put("productPage", response.getData());
        map.put("currentPage", page);
        map.put("size", pageSize);
        return new ModelAndView("product/list",map);
    }

    /**
     * 商品上架
     * @param productId 商品id
     * @return
     */
    @RequestMapping(value = "/productUpFrom/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView productUpFrom(@PathVariable String productId,Map<String,Object> map){
        if (StringUtils.isEmpty(productId)){
            log.error("OP[]ProductController[]productUpFrom[]product id is null");
            map.put("msg","商品上架失败");
            return new ModelAndView("/error",map);
        }
        Response<Boolean> booleanResponse = productService.productUpFrom(productId);
        if (!booleanResponse.getData()){
            map.put("msg","商品上架失败");
            return new ModelAndView("/error",map);
        }
        return new ModelAndView("redirect:/admin/product/list");
    }

    /**
     * 商品下架
     * @param productId
     * @param map
     * @return
     */
    @RequestMapping(value = "/productDownFrom/{productId}", produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView productDownFrom(@PathVariable String productId,Map<String,Object> map){
        if (StringUtils.isEmpty(productId)){
            log.error("OP[]ProductController[]productDownFrom[]product id is null");
            map.put("msg","商品下架失败");
            return new ModelAndView("/error",map);
        }
        Response<Boolean> booleanResponse = productService.productDownFrom(productId);
        if (!booleanResponse.getData()){
            map.put("msg","商品下架失败");
            return new ModelAndView("/error",map);
        }
        return new ModelAndView("redirect:/admin/product/list");
    }

    /**
     * 添加或编辑商品
     * @param productInfo
     * @return
     */
    @PostMapping(value = "/saveOrEdit", produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView saveOrEdit(@ModelAttribute("form") ProductInfo productInfo, Map<String,Object> map){
        if (ObjectUtils.isEmpty(productInfo)){
            log.error("OP[]ProductController[]save[]param is null");
            map.put("msg","参数不正确");
            return new ModelAndView("/error",map);
        }
        Response<Boolean> response =null;
        if (StringUtils.isEmpty(productInfo.getProductId())){
            response = productService.save(productInfo);
        } else {
            response = productService.update(productInfo);
        }
        if (!response.getData()){
            map.put("url","/sell/product/list");
            map.put("msg","参数不正确");
            return new ModelAndView("/error",map);
        }
        return new ModelAndView("redirect:/admin/product/list");
    }

    @GetMapping("/index")
    public ModelAndView toEdit(@RequestParam(required = false) String  productId, Map<String,Object> map){

        if (!StringUtils.isEmpty(productId)){
            Response<ProductInfo> infoResponse = productService.findOne(productId);
            map.put("product",infoResponse.getData());
        }
        // 查询所有类目
        List<ProductCategory> categoryList = productCategoryService.findAll();
        map.put("categoryList",categoryList);
        return new ModelAndView("/product/index",map);

    }
}
