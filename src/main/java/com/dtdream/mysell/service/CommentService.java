package com.dtdream.mysell.service;

import com.dtdream.mysell.dto.Response;
import com.dtdream.mysell.model.Comment;

/**
 * 评论
 * @Author yxiumei
 * @Data 2019/2/16 14:55
 */
public interface CommentService {

    /**
     * 新增评论
     * @param comment
     * @return
     */
    Response insert(Comment comment);

    /**
     * 通过id，查询评论
     * @param id
     * @return
     */
    Response<Comment> findCommentById(Integer id);
}
