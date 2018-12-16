package com.dtdream.mysell.controller;

import com.dtdream.mysell.dto.Response;
import com.dtdream.mysell.dto.ShopImagesDto;
import com.dtdream.mysell.model.Shop;
import com.dtdream.mysell.service.ShopService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * 店铺信息
 * @Author yxiumei
 * @Data 2018/12/12 20:19
 */
@Slf4j
@Controller
@RequestMapping(value = "/admin/shop")
public class ShopController {

    @Autowired
    private ShopService shopService;

    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                             @RequestParam(required = false) String key,
                             Map<String,Object> map){
        Response<PageInfo<Shop>> response = shopService.findAll(page,pageSize,key);
        map.put("productPage", response.getData());
        map.put("currentPage", page);
        map.put("size", pageSize);
        return new ModelAndView("shop/list",map);
    }


    @GetMapping("/index")
    public ModelAndView toEdit(@RequestParam(required = false) String  shopId, Map<String,Object> map){

        if (!StringUtils.isEmpty(shopId)){
            Response<ShopImagesDto> infoResponse = shopService.findOne(shopId);
            map.put("shopDto",infoResponse.getData());
        }
        return new ModelAndView("/shop/index",map);
    }


    @PostMapping(value = "/saveOrEdit", produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView saveOrEdit(@ModelAttribute("form") ShopImagesDto shop, Map<String,Object> map){
        String id = shop.getShop().getId();
        Response<Boolean> response = null;
        if (!StringUtils.isEmpty(id)){
            response = shopService.update(shop);
        } else {
            response = shopService.save(shop);
        }
        if (!response.isSuccess()){
            map.put("url","/sell/shop/list");
            map.put("msg",response.getMsg());
            return new ModelAndView("/error",map);
        }
        return new ModelAndView("redirect:/admin/product/list");
    }


}
