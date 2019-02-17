package com.dtdream.mysell.controller;

import com.dtdream.mysell.dto.CommentDto;
import com.dtdream.mysell.dto.Response;
import com.dtdream.mysell.model.Comment;
import com.dtdream.mysell.service.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author yxiumei
 * @Data 2019/2/16 19:39
 */
@RestController
@RequestMapping("/comment")
public class CommentController {

    @Autowired
    private CommentService commentService;

    @RequestMapping(value = "/insert", method = RequestMethod.POST,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<Boolean> insert(@RequestBody CommentDto commentDto) {
        return commentService.insert(commentDto);
    }

    @RequestMapping(value = "/findAll", method = RequestMethod.GET,
            produces = MediaType.APPLICATION_JSON_VALUE)
    public Response<Comment> finAll() {
        return commentService.findAll();
    }
}
