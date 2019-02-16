package com.dtdream.mysell.service.imp;

import com.alibaba.fastjson.JSON;
import com.dtdream.mysell.dto.Response;
import com.dtdream.mysell.enums.CommentTypeEnum;
import com.dtdream.mysell.enums.ErrorMessage;
import com.dtdream.mysell.mapper.*;
import com.dtdream.mysell.model.*;
import com.dtdream.mysell.service.CommentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.CollectionUtils;
import org.springframework.util.StringUtils;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @Author yxiumei
 * @Data 2019/2/16 14:57
 */
@Slf4j
@Service
public class CommentServiceImpl implements CommentService {

    @Autowired(required = false)
    private OrderMasterMapper orderMasterMapper;
    @Autowired(required = false)
    private CommentMapper commentMapper;
    @Autowired(required = false)
    private UserInfoMapper userInfoMapper;
    @Autowired(required = false)
    private ShopScoreMapper shopScoreMapper;
    @Autowired(required = false)
    private OrderDetailMapper orderDetailMapper;

    @Override
    public Response<Boolean> insert(Comment comment) {
        try {
            if (null == comment || !StringUtils.isEmpty(comment.getOrderId())) {
                log.error("OP[]CommentServiceImpl[]insert[]param is null");
                return Response.fail(ErrorMessage.PARAM_IS_NULL.toString());
            }
            // 查询订单信息
            String orderId = comment.getOrderId();
            OrderMaster orderMaster = orderMasterMapper.selectByPrimaryKey(orderId);
            if (null == orderMaster) {
                log.error("OP[]CommentServiceImpl[]insert[]orderId:{},order is null", orderId);
            }
            // 查询店铺信息,目前店铺信息唯一
            List<ShopScore> all = shopScoreMapper.findAll();
            if (CollectionUtils.isEmpty(all)) {
                log.error("OP[]CommentServiceImpl[]insert[]shop is null");
                return Response.fail(ErrorMessage.PARAM_IS_NULL.toString());
            }
            ShopScore shopScore = all.get(0);
            // 计算评分
            if (CommentTypeEnum.GOOD_COMMENT.getCode().equals(comment.getRateType())) {
                // 好评 + 1
                Integer highOpinion = shopScore.getHighOpinion();
                shopScore.setHighOpinion(highOpinion + 1);
                double serviceSocre = shopScore.getServiceScore() + 0.1;
                double foodScore = shopScore.getFoodScore() + 0.1;
                if (serviceSocre >= 5) {
                    //服务态度评分 最高5分
                    serviceSocre = 5;
                }
                if (foodScore >= 5) {
                    foodScore = 5;
                }
                shopScore.setServiceScore(serviceSocre);
                shopScore.setFoodScore(foodScore);
            } else {
                // 差评 + 1
                Integer differOpinion = shopScore.getDifferOpinion();
                shopScore.setDifferOpinion(differOpinion + 1);
                double serviceSocre = shopScore.getServiceScore() - 0.1;
                double foodScore = shopScore.getFoodScore() - 0.1;
                if (serviceSocre < 0) {
                    serviceSocre = 0;
                }
                if (foodScore < 0) {
                    foodScore = 0;
                }
                shopScore.setServiceScore(serviceSocre);
                shopScore.setFoodScore(foodScore);
            }
            // 总评论数 + 1
            Integer ratingCount = shopScore.getRatingCount() + 1;
            shopScore.setRatingCount(ratingCount);
            // 综合评分
            double score = shopScore.getServiceScore() + shopScore.getFoodScore();
            double v = score / 2;
            shopScore.setScore(v);
            shopScore.setSellCount(shopScore.getSellCount() + 1);

            String buyerOpenid = orderMaster.getBuyerOpenid();
            // 通过open id 查询用户头像
            UserInfo userInfo = userInfoMapper.selectByOpenId(buyerOpenid);
            if (null == userInfo) {
                log.error("OP[]CommentServiceImpl[]insert[]openId:{},user info is null", buyerOpenid);
                return Response.fail(ErrorMessage.PARAM_IS_NULL.toString());
            }
            comment.setAvatar(userInfo.getUserImg());
            comment.setUsername(userInfo.getUsername());
            String recommend = comment.getRecommend();
            // 推荐本次购买商品
            if (!StringUtils.isEmpty(recommend)) {
                List<OrderDetail> byOrder = orderDetailMapper.findByOrder(orderId);
                if (!CollectionUtils.isEmpty(byOrder)) {
                    List<String> productName = byOrder.stream()
                            .map(OrderDetail::getProductName).collect(Collectors.toList());
                    String s = JSON.toJSONString(productName);
                    comment.setRecommend(s);
                }
            }
            Date date= new Date();
            comment.setRateTime(date);
            comment.setUpdateTime(date);
            comment.setCreateTime(date);
            commentMapper.insert(comment);
        } catch (Exception e) {
            log.error("OP[]CommentServiceImpl[]insert[]insert comment fail:{}", e.fillInStackTrace());
            return Response.fail(ErrorMessage.COMMENT_FAIL.toString());

        }
        return null;
    }

    @Override
    public Response<Comment> findCommentById(Integer id) {
        return null;
    }

}
