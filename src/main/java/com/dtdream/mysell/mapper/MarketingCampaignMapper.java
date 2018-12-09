package com.dtdream.mysell.mapper;

import com.dtdream.mysell.model.MarketingCampaign;

/**
 * 营销活动
 * @author yxiumei
 */
public interface MarketingCampaignMapper {

    /**
     * 保存
     * @param record
     * @return
     */
    int insert(MarketingCampaign record);

    /**
     * 通过id查询
     * @param id
     * @return
     */
    MarketingCampaign selectByPrimaryKey(Integer id);

    /**
     * 更新
     * @param record
     * @return
     */
    int update(MarketingCampaign record);

}