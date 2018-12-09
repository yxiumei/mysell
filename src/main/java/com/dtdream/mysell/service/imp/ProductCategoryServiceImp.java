package com.dtdream.mysell.service.imp;

import ch.qos.logback.classic.Logger;
import com.dtdream.mysell.dto.Response;
import com.dtdream.mysell.mapper.ProductCategoryMapper;
import com.dtdream.mysell.model.ProductCategory;
import com.dtdream.mysell.service.ProductCategoryService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import com.sun.org.apache.xpath.internal.operations.Bool;
import lombok.extern.slf4j.Slf4j;
import org.omg.PortableInterceptor.INACTIVE;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
@Slf4j
public class ProductCategoryServiceImp implements ProductCategoryService {

    @Autowired
    private ProductCategoryMapper productCategoryMapper;

    @Override
    public int deleteByPrimaryKey(Integer categoryId) {
        int i = productCategoryMapper.deleteByPrimaryKey(categoryId);
        return i;
    }

    @Override
    public int insert(ProductCategory record) {
        return 0;
    }

    @Override
    public Response<Boolean> insertSelective(ProductCategory record) {
        record.setCreateTime(new Date());
        record.setUpdateTime(new Date());
        Integer insert = productCategoryMapper.insert(record);
        if (insert != 1){
            log.error("OP[]ProductCategoryServiceImp[]insertSelective[]save category fail");
            return Response.ok(Boolean.FALSE);
        }
        return Response.ok(Boolean.TRUE);
    }

    @Override
    public ProductCategory selectByPrimaryKey(Integer categoryId) {
        ProductCategory category = productCategoryMapper.selectByPrimaryKey(categoryId);
        return category;
    }

    @Override
    public Response<Boolean> updateByPrimaryKeySelective(ProductCategory record) {
        record.setUpdateTime(new Date());
        Integer i = productCategoryMapper.updateByPrimaryKeySelective(record);
        if (i != 1){
            log.error("OP[]ProductCategoryServiceImp[]updateByPrimaryKeySelective[]update category fail");
            return Response.ok(Boolean.FALSE);
        }
        return Response.ok(Boolean.TRUE);
    }

    @Override
    public int updateByPrimaryKey(ProductCategory record) {
        return 0;
    }

    @Override
    public List<ProductCategory> findCategoryByType(List<Integer> list) {
        return productCategoryMapper.findCategoryByType(list);
    }

    @Override
    public List<ProductCategory> findAll() {
        List<ProductCategory> all = productCategoryMapper.findAll();
        return all;
    }

    @Override
    public PageInfo<ProductCategory> findCategoryByCategoryName(Integer pageNo, Integer pageSize,String categoryName) {
        Page page = PageHelper.startPage(pageNo, pageSize);
        List<ProductCategory> categoryByCategoryName = productCategoryMapper.findCategoryByCategoryName(categoryName);
        PageInfo<ProductCategory> pageInfo = new PageInfo<>(page);
        pageInfo.setList(categoryByCategoryName);
        return pageInfo;
    }
}
