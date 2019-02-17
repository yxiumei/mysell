package com.dtdream.mysell.service.imp;

import com.alibaba.fastjson.JSON;
import com.dtdream.mysell.dto.CommentDto;
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

    private final static Integer VALUE = 3;

    @Override
    public Response<Boolean> insert(CommentDto commentDto) {
        try {
            if (null == commentDto || StringUtils.isEmpty(commentDto.getOrderId())) {
                log.error("OP[]CommentServiceImpl[]insert[]param is null");
                return Response.fail(ErrorMessage.PARAM_IS_NULL.toString());
            }
            Comment comment = new Comment();
            // 查询订单信息
            String orderId = commentDto.getOrderId();
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
            // 通过服务评分，商品计算是评论类型
            Integer i = commentDto.getServiceScore() + commentDto.getFoodScore();
            int count = i / 2;
            if (count >= VALUE) {
                // 好评
                comment.setRateType(CommentTypeEnum.GOOD_COMMENT.getCode());
                // 好评 + 1
                Integer highOpinion = shopScore.getHighOpinion();
                shopScore.setHighOpinion(highOpinion + 1);
            } else {
                comment.setRateType(CommentTypeEnum.NEGATIVE_COMMENT.getCode());
                // 差评 + 1
                Integer differOpinion = shopScore.getDifferOpinion();
                shopScore.setDifferOpinion(differOpinion + 1);
            }
            // 计算评分
            comment.setScore(count);
            // 计算服务评分
            double serviceSocre;
            if (commentDto.getServiceScore() >= VALUE) {
                // 服务评分好评
                serviceSocre = shopScore.getServiceScore() + 0.1;
                if (serviceSocre >= 5) {
                    //服务态度评分 最高5分
                    serviceSocre = 5;
                }
            } else {
                serviceSocre = shopScore.getServiceScore() - 0.1;
                if (serviceSocre <= 0) {
                    serviceSocre = 0;
                }
            }
            shopScore.setServiceScore(serviceSocre);

            // 计算商品评分
            double foodScore ;
            if (commentDto.getFoodScore() >= VALUE) {
                // 好评
                foodScore = shopScore.getFoodScore() + 0.1;
                if (foodScore >= 5) {
                    foodScore = 5;
                }
            } else {
                foodScore = shopScore.getFoodScore() - 0.1;
                if (foodScore < 0) {
                    foodScore = 0;
                }
            }
            shopScore.setFoodScore(foodScore);

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
            Integer recommend = commentDto.getRecommend();
            // 推荐本次购买商品
            if (0 == recommend) {
                List<OrderDetail> byOrder = orderDetailMapper.findByOrder(orderId);
                if (!CollectionUtils.isEmpty(byOrder)) {
                    List<String> productName = byOrder.stream()
                            .map(OrderDetail::getProductName).collect(Collectors.toList());
                    String s = JSON.toJSONString(productName);
                    comment.setRecommends(s);
                }
            }
            Date date= new Date();
            comment.setOrderId(commentDto.getOrderId());
            comment.setText(commentDto.getText());
            comment.setRateTime(date);
            comment.setUpdateTime(date);
            comment.setCreateTime(date);
            commentMapper.insert(comment);
            // 更新评分
            shopScoreMapper.update(shopScore);
            return Response.ok(Boolean.TRUE);
        } catch (Exception e) {
            log.error("OP[]CommentServiceImpl[]insert[]insert comment fail:{}", e.fillInStackTrace());
            return Response.fail(ErrorMessage.COMMENT_FAIL.toString());

        }

    }

    @Override
    public Response<Comment> findCommentById(Integer id) {
        return null;
    }

    @Override
    public Response<Comment> findAll() {
        List<Comment> all = commentMapper.findAll();
        if (!CollectionUtils.isEmpty(all)) {
            all.forEach(comment -> {
                String recommend = comment.getRecommends();
                List<String> list = JSON.parseArray(recommend, String.class);
                comment.setRecommend(list);
            });
        }
        return Response.ok(all);
    }
}
