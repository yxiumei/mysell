package com.dtdream.mysell.controller;

import com.dtdream.mysell.dto.Response;
import com.dtdream.mysell.model.ProductCategory;
import com.dtdream.mysell.service.ProductCategoryService;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.util.Map;

/**
 * @Author yxiumei
 * @Data 2018/12/9 15:27
 */
@Slf4j
@Controller
@RequestMapping("/admin/category")
public class CategoryController {

    @Autowired
    ProductCategoryService productCategoryService;

    /**
     * 查询所有类目
     * @param page
     * @param pageSize
     * @param key
     * @param map
     * @return
     */
    @GetMapping(value = "/list", produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView list(@RequestParam(value = "page",defaultValue = "1") Integer page,
                             @RequestParam(value = "pageSize",defaultValue = "5") Integer pageSize,
                             @RequestParam(required = false) String key,
                             Map<String,Object> map){
        PageInfo<ProductCategory> response = productCategoryService.findCategoryByCategoryName(page, pageSize, key);
        map.put("categoryPage", response);
        map.put("currentPage", page);
        map.put("size", pageSize);
        return new ModelAndView("category/list",map);
    }

    /**
     * 跳转到添加或编辑页
     * @param categoryId
     * @return
     */
    @GetMapping(value = "/index", produces = MediaType.APPLICATION_JSON_VALUE)
    public ModelAndView index(@RequestParam(required = false) Integer categoryId,Map<String,Object> map) {
        if ( null != categoryId){
            ProductCategory category = productCategoryService.selectByPrimaryKey(categoryId);
            map.put("category",category);
        }
        return new ModelAndView("/category/index",map);
    }

    /**
     * 添加或编辑类目
     * @param productCategory
     * @return
     */
    @PostMapping(value = "/saveOrEdit", produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
    public ModelAndView saveOrEdit(@ModelAttribute("form") ProductCategory productCategory, Map<String,Object> map){
        Integer categoryId = productCategory.getCategoryId();
        Response<Boolean> response = null;
        if (null != categoryId){
            response = productCategoryService.updateByPrimaryKeySelective(productCategory);
        } else {
            response = productCategoryService.insertSelective(productCategory);
        }
        if (!response.getData()){
            map.put("url","/sell/category/list");
            map.put("msg","参数不正确");
            return new ModelAndView("/error",map);
        }
        return new ModelAndView("redirect:/admin/category/list");
    }
}
