package com.dtdream.mysell.service.imp;

import com.dtdream.mysell.dto.Response;
import com.dtdream.mysell.enums.ShopEnum;
import com.dtdream.mysell.manage.ShopManage;
import com.dtdream.mysell.mapper.ShopMapper;
import com.dtdream.mysell.model.Shop;
import com.dtdream.mysell.service.ShopService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

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
    public Response<Boolean> save(Shop shop) {
        try {
            return Response.ok(shopManage.save(shop));
        }catch (Exception e){
            log.error("OP[]ShopServiceImp[]save[]save shop info fail");
            return Response.fail("save shop info fail");
        }
    }

    @Override
    public Response<Boolean> update(Shop shop) {
        shopMapper.update(shop);
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
}
