package com.dtdream.mysell.service;

import com.dtdream.mysell.dto.Response;
import com.dtdream.mysell.model.MarketingCampaign;

/**
 * @Author yxiumei
 * @Data 2018/12/10 23:05
 */
public interface MarketingCampaignService {

    /**
     * 创建活动
     * @param marketingCampaign
     * @return
     */
    Response<Boolean> save(MarketingCampaign marketingCampaign);

    /**
     * 更新活动
     * @param marketingCampaign
     * @return
     */
    Response<Boolean> update(MarketingCampaign marketingCampaign);

    /**
     * 删除活动
     * @param activityId
     * @return
     */
    Response<Boolean> delete(Integer activityId);
}
