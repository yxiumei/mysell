package com.dtdream.mysell.manage;

import com.alibaba.fastjson.JSON;
import com.dtdream.mysell.dto.ShopImagesDto;
import com.dtdream.mysell.enums.ShopEnum;
import com.dtdream.mysell.mapper.ShopDetailMapper;
import com.dtdream.mysell.mapper.ShopMapper;
import com.dtdream.mysell.mapper.ShopScoreMapper;
import com.dtdream.mysell.model.Shop;
import com.dtdream.mysell.model.ShopDetail;
import com.dtdream.mysell.model.ShopScore;
import com.dtdream.mysell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @Author yxiumei
 * @Data 2018/12/10 22:41
 */
@Component
@Slf4j
public class ShopManage {

    @Autowired
    private ShopMapper shopMapper;
    @Autowired
    private ShopScoreMapper shopScoreMapper;
    @Autowired
    private ShopDetailMapper shopDetailMapper;

    private final double INIT_VALUE = 50;

    @Transactional(rollbackFor = Exception.class)
    public Boolean save(ShopImagesDto shopImagesDto) throws Exception {
        List<String> list = new ArrayList<>();
        Shop shop = shopImagesDto.getShop();
        Date date = new Date();
        String key = KeyUtil.getUniqueKey();
        shop.setCreateTime(date);
        shop.setUpdateTime(date);
        shop.setId(key);
        shop.setStatus(ShopEnum.NORMAL.getCode());
        Integer insert = shopMapper.insert(shop);
        if (insert != 1) {
            log.error("OP[]ProductManage[]save[]save shop fail");
            throw new Exception();
        }
        ShopScore shopScore = new ShopScore();
        shopScore.setShopId(key);
        shopScore.setScore(INIT_VALUE);
        shopScore.setServiceScore(INIT_VALUE);
        shopScore.setFoodScore(INIT_VALUE);
        shopScore.setRankRate(INIT_VALUE);
        shopScore.setCreateTime(date);
        shopScore.setUpdateTime(date);
        int insert1 = shopScoreMapper.insert(shopScore);
        if (insert1 != 1) {
            log.error("OP[]ProductManage[]save[]save shop score fail");
            throw new Exception();
        }
        ShopDetail shopDetail = new ShopDetail();
        shopDetail.setShopId(key);
        shopDetail.setBulletin(shopImagesDto.getBulletin());
        shopDetail.setInfos(shopImagesDto.getInfos());
        list.add(shopImagesDto.getShopImg1());
        list.add(shopImagesDto.getShopImg2());
        list.add(shopImagesDto.getShopImg3());
        list.add(shopImagesDto.getShopImg4());
        String s = JSON.toJSONString(list);
        shopDetail.setPics(s);
        shopDetail.setCreateTime(date);
        shopDetail.setUpdateTime(date);
        shopDetailMapper.insert(shopDetail);
        return Boolean.TRUE;

    }
}
