package com.dtdream.mysell.service.imp;

import com.dtdream.mysell.dto.Response;
import com.dtdream.mysell.enums.ShopEnum;
import com.dtdream.mysell.mapper.MarketingCampaignMapper;
import com.dtdream.mysell.model.MarketingCampaign;
import com.dtdream.mysell.model.Shop;
import com.dtdream.mysell.service.MarketingCampaignService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;

/**
 * @Author yxiumei
 * @Data 2018/12/10 23:09
 */
@Slf4j
@Service
public class MarketingCampaignServiceImpl implements MarketingCampaignService {

    @Autowired
    MarketingCampaignMapper marketingCampaignMapper;

    @Override
    public Response<Boolean> save(MarketingCampaign marketingCampaign) {
        try{
            Date date = new Date();
            marketingCampaign.setCreateTime(date);
            marketingCampaign.setUpdateTime(date);
            marketingCampaign.setStatus(ShopEnum.NORMAL.getCode());
            marketingCampaignMapper.insert(marketingCampaign);
            return Response.ok(Boolean.TRUE);
        }catch (Exception e){
            log.error("OP[]MarketingCampaignServiceImpl[]save[]create activity fail");
            return Response.fail("create activity fail");
        }

    }

    @Override
    public Response<Boolean> update(MarketingCampaign marketingCampaign) {
        try {
            marketingCampaign.setUpdateTime(new Date());
            marketingCampaignMapper.update(marketingCampaign);
            return Response.ok(Boolean.TRUE);
        }catch (Exception e){
            log.error("OP[]MarketingCampaignServiceImpl[]save[]update activity fail");
            return Response.fail("update activity fail");
        }

    }

    @Override
    public Response<Boolean> delete(Integer activityId) {
        try {
            marketingCampaignMapper.delete(activityId);
            return Response.ok(Boolean.TRUE);
        }catch (Exception e){
            log.error("OP[]MarketingCampaignServiceImpl[]save[]update activity fail");
            return Response.fail("update activity fail");
        }
    }
}
