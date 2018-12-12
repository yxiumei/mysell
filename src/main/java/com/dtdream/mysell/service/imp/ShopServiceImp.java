package com.dtdream.mysell.service.imp;

import com.dtdream.mysell.dto.Response;
import com.dtdream.mysell.dto.ShopImagesDto;
import com.dtdream.mysell.enums.ShopEnum;
import com.dtdream.mysell.manage.ShopManage;
import com.dtdream.mysell.mapper.ShopMapper;
import com.dtdream.mysell.model.Shop;
import com.dtdream.mysell.service.ShopService;
import com.github.pagehelper.Page;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

/**
 * @Author yxiumei
 * @Data 2018/12/10 22:32
 */
@Slf4j
@Service
public class ShopServiceImp implements ShopService {

    @Autowired
    private ShopMapper shopMapper;
    @Autowired
    private ShopManage shopManage;

    @Override
    public Response<Boolean> save(ShopImagesDto shop) {
        try {
            return Response.ok(shopManage.save(shop));
        }catch (Exception e){
            log.error("OP[]ShopServiceImp[]save[]save shop info fail：error:{}",e.getStackTrace());
            return Response.fail("save shop info fail");
        }
    }

    @Override
    public Response<Boolean> update(ShopImagesDto shopImagesDto) {
        //shopMapper.update(shop);
        return Response.ok(Boolean.TRUE);
    }

    @Override
    public Response<Boolean> cancel(String shopId) {
        Shop shop = shopMapper.selectByPrimaryKey(shopId);
        shop.setStatus(ShopEnum.CANCEL.getCode());
        shop.setUpdateTime(new Date());
        shopMapper.update(shop);
        return Response.ok(Boolean.TRUE);
    }

    @Override
    public Response<Shop> findOne(String shopId) {
        Shop shop = shopMapper.selectByPrimaryKey(shopId);
        return Response.ok(shop);
    }

    @Override
    public Response<PageInfo<Shop>> findAll(Integer pageNo, Integer pageSize, String key) {
        Page page = PageHelper.startPage(pageNo, pageSize);
        List<Shop> all = shopMapper.findAll(key);
        PageInfo<Shop> pageInfo = new PageInfo<>(page);
        pageInfo.setList(all);
        return Response.ok(pageInfo);
    }
}
