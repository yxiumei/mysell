package com.dtdream.mysell.mapper;

import com.dtdream.mysell.model.MarketingCampaign;
import org.apache.ibatis.annotations.Param;

import java.util.List;

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

    /**
     * 删除活动
     * @param activityId
     */
    int delete(@Param("activityId") Integer activityId);

    /**
     * 查询所有活动信息
     * @param activityName
     * @return
     */
    List<MarketingCampaign> findAll(@Param("activityName") String activityName);
}