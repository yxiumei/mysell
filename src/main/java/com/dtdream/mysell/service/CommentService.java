package com.dtdream.mysell.service;

import com.dtdream.mysell.dto.CommentDto;
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
     * @param commentDto 评论请求参数
     * @return
     */
    Response<Boolean> insert(CommentDto commentDto);

    /**
     * 通过id，查询评论
     * @param id
     * @return
     */
    Response<Comment> findCommentById(Integer id);

    /**
     * 查询所有评论
     * @return
     */
    Response<Comment> findAll();
}
