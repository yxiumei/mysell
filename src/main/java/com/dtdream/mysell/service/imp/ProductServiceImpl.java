package com.dtdream.mysell.service.imp;

import com.dtdream.mysell.dto.*;
import com.dtdream.mysell.enums.ErrorMessage;
import com.dtdream.mysell.enums.ProductEnum;
import com.dtdream.mysell.manage.ProductManage;
import com.dtdream.mysell.mapper.ProductCategoryMapper;
import com.dtdream.mysell.mapper.ProductInfoMapper;
import com.dtdream.mysell.model.ProductCategory;
import com.dtdream.mysell.model.ProductInfo;
import com.dtdream.mysell.service.ProductService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author yxiumei
 */
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoMapper productInfoMapper;
    @Autowired
    private ProductCategoryMapper productCategoryMapper;
    @Autowired
    private ProductManage productManage;

    @Override
    public Response<List<ProductDto>> findUpFrameProduct() {
        // 查询上架商品
        List<ProductInfo> upFrameProduct = productInfoMapper.findUpFrameProduct(ProductEnum.DOWN_FRAME.getCode());
        if (upFrameProduct.size() == 0){
            log.error("OP[]service[]ProductServiceImpl[]findUpFrameProduct[]upFrameProduct is null");
            return Response.fail(ErrorMessage.GET_UP_FRAME_PRODUCT_FAIL_.toString());
        }
        List<Integer> categoryTypeList = upFrameProduct.stream()
                .map(ProductInfo::getCategoryType)
                .collect(Collectors.toList());
        // 查询所有类目
        List<ProductCategory> categoryByType =
                productCategoryMapper.findCategoryByType(categoryTypeList);
        if (categoryByType.size() == 0){
            log.error("OP[]service[]ProductServiceImpl[]findUpFrameProduct[]categoryByType is null");
            return Response.fail(ErrorMessage.GET_ALL_CATEGROY_FAIL.toString());
        }
        List<ProductDto> listProductDto = new ArrayList<>();
        for (ProductCategory productCategory: categoryByType) {
            ProductDto productDto = new ProductDto();
            productDto.setCategoryName(productCategory.getCategoryName());
            productDto.setCategoryType(productCategory.getCategoryType());
            List<ProductInfoDto> list = new ArrayList<>();
            for (ProductInfo productInfo : upFrameProduct){
                if (productInfo.getCategoryType().equals(productCategory.getCategoryType())){
                    ProductInfoDto productInfoDto = new ProductInfoDto();
                    BeanUtils.copyProperties(productInfo, productInfoDto);
                    list.add(productInfoDto);
                }
            }
            productDto.setProductInfoDto(list);
            listProductDto.add(productDto);
        }
        return Response.ok(listProductDto);
    }

    @Override
    public Response<Boolean> increaseStock(List<CartDto> cartDtoList) {
        try {
            Response<Boolean> booleanResponse = productManage.increaseStock(cartDtoList);
            return booleanResponse;
        } catch (Exception e) {
            log.error("OP[]ProductServiceImpl[]increaseStock[]increaseStock fail :{}", e.fillInStackTrace());
            return Response.fail(ErrorMessage.UPDATE_PRODUCT_STOCK_FAIL.toString());
        }
    }

    @Override
    public Response<Boolean> decreaseStock(List<CartDto> cartDtoList) {
        try{
            return productManage.decreaseStock(cartDtoList);
        }catch (Exception e){
            log.error("OP[]ProductServiceImpl[]decreaseStock[]decreaseStock fail :{}", e.fillInStackTrace());
            return Response.fail(ErrorMessage.UPDATE_PRODUCT_STOCK_FAIL.toString());
        }

    }

    @Override
    public Response<PageInfo<ProductContainCategoryDto>> productList(Integer pageNo, Integer pageSize, String key) {
        Page page = PageHelper.startPage(pageNo, pageSize);
        List<ProductInfo> list = productInfoMapper.findList(key);
        if (list.size() == 0){
            log.error("OP[]service[]ProductServiceImpl[]productList[]Product list is null");
            return Response.fail(ErrorMessage.GET__PRODUCT_LIST_FAIL_.toString());
        }
        List<Integer> categoryTypeList = list.stream()
                .map(ProductInfo::getCategoryType)
                .collect(Collectors.toList());
        // 查询所有类目
        List<ProductCategory> categoryByType =
                productCategoryMapper.findCategoryByType(categoryTypeList);
        List<ProductContainCategoryDto> productContainCategoryDtos = new ArrayList<>();
        for (ProductInfo info : list){
            ProductContainCategoryDto containCategoryDto = new ProductContainCategoryDto();
            containCategoryDto.setProductInfo(info);
            Integer categoryType = info.getCategoryType();
            for (ProductCategory category : categoryByType){
                if(category.getCategoryType().equals(categoryType)){
                    containCategoryDto.setCategoryName(category.getCategoryName());
                    productContainCategoryDtos.add(containCategoryDto);
                    break;
                }
            }
        }
        PageInfo<ProductContainCategoryDto> pageInfo = new PageInfo<>(page);
        pageInfo.setList(productContainCategoryDtos);
        return Response.ok(pageInfo);
    }

    @Override
    public Response<Boolean> productUpFrom(String productId) {
        ProductInfo info = productInfoMapper.selectByPrimaryKey(productId);
        if (!ProductEnum.UP_FRAME.getCode().equals(info.getStatus())){
            log.error("OP[]ProductServiceImpl[]productUpFrom[]product status error");
            return Response.ok(Boolean.FALSE);
        }
        info.setStatus(ProductEnum.DOWN_FRAME.getCode());
        info.setUpdateTime(new Date());
        Integer i = productInfoMapper.updateByPrimaryKeySelective(info);
        if (i != 1){
            log.error("OP[]ProductServiceImpl[]productUpFrom[]update product fail");
            return Response.ok(Boolean.FALSE);
        }
        return Response.ok(Boolean.TRUE);
    }

    @Override
    public Response<Boolean> productDownFrom(String productId) {
        ProductInfo info = productInfoMapper.selectByPrimaryKey(productId);
        if (!ProductEnum.DOWN_FRAME.getCode().equals(info.getStatus())){
            log.error("OP[]ProductServiceImpl[]productDownFrom[]product status error");
            return Response.ok(Boolean.FALSE);
        }
        info.setStatus(ProductEnum.UP_FRAME.getCode());
        info.setUpdateTime(new Date());
        Integer i = productInfoMapper.updateByPrimaryKeySelective(info);
        if (i != 1){
            log.error("OP[]ProductServiceImpl[]productDownFrom[]update product fail");
            return Response.ok(Boolean.FALSE);
        }
        return Response.ok(Boolean.TRUE);
    }
}
