package com.dtdream.mysell.mapper;

import com.dtdream.mysell.model.Comment;
import org.apache.ibatis.annotations.Param;

/**
 * 评论管理
 * @Author yxiumei
 * @Data 2019/2/16 14:51
 */
public interface CommentMapper {

    /**
     * 通过id,查询评论
     * @param id 评论id
     * @return
     */
    Comment selectByPrimaryKey(@Param("id") Integer id);

    /**
     * 增加评分
     * @param comment
     * @return
     */
    int insert(Comment comment);
}
