package com.dtdream.mysell.manage;

import com.dtdream.mysell.enums.ShopEnum;
import com.dtdream.mysell.mapper.ShopMapper;
import com.dtdream.mysell.mapper.ShopScoreMapper;
import com.dtdream.mysell.model.Shop;
import com.dtdream.mysell.model.ShopScore;
import com.dtdream.mysell.utils.KeyUtil;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

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

    private final double INIT_VALUE = 50;

    @Transactional(rollbackFor = Exception.class)
    public Boolean save(Shop shop) throws Exception {
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
        return Boolean.TRUE;

    }
}
