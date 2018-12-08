package com.dtdream.mysell.service.imp;

import com.dtdream.mysell.mapper.ProductCategoryMapper;
import com.dtdream.mysell.model.ProductCategory;
import com.dtdream.mysell.service.ProductCategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
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
    public int insertSelective(ProductCategory record) {
        return 0;
    }

    @Override
    public ProductCategory selectByPrimaryKey(Integer categoryId) {
        return null;
    }

    @Override
    public int updateByPrimaryKeySelective(ProductCategory record) {
        return 0;
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
}
