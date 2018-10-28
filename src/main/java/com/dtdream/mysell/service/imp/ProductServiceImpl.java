package com.dtdream.mysell.service.imp;

import com.dtdream.mysell.dto.CartDto;
import com.dtdream.mysell.dto.ProductDto;
import com.dtdream.mysell.dto.ProductInfoDto;
import com.dtdream.mysell.dto.Response;
import com.dtdream.mysell.enums.ErrorMessage;
import com.dtdream.mysell.enums.ProductEnum;
import com.dtdream.mysell.mapper.ProductCategoryMapper;
import com.dtdream.mysell.mapper.ProductInfoMapper;
import com.dtdream.mysell.model.ProductCategory;
import com.dtdream.mysell.model.ProductInfo;
import com.dtdream.mysell.service.ProductService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author 杨秀眉
 */
@Service
@Slf4j
public class ProductServiceImpl implements ProductService {

    @Autowired
    private ProductInfoMapper productInfoMapper;
    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Override
    public Response findUpFrameProduct() {
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

        return Response.ok(Boolean.TRUE);
    }

    @Override
    public Response<Boolean> decreaseStock(List<CartDto> cartDtoList) {
        for (CartDto cartDto: cartDtoList){
            ProductInfo productInfo = productInfoMapper.selectByPrimaryKey(cartDto.getProductId());
            if (null == productInfo){
                return Response.fail(ErrorMessage.PRODUCT_NOT_EXIST.toString());
            }
            Integer result = productInfo.getProductStock() - cartDto.getProductQuantity();
            if (result < 0){
                Response.fail(ErrorMessage.INVENTORY_SHORTAGE.toString());
            }
            productInfo.setProductStock(result);
            Integer i = productInfoMapper.updateByPrimaryKeySelective(productInfo);
            if (i <= 0){
                Response.fail(ErrorMessage.UPDATE_PRODUCT_STOCK_FAIL.toString());
            }
        }
        return Response.ok(Boolean.TRUE);
    }
}
