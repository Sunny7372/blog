package com.blogapp12.service;

import com.blogapp12.payload.CommentDto;
import com.blogapp12.payload.PostWithCommentDto;

import java.util.List;

public interface CommentService {
    CommentDto createComment(CommentDto commentDto,long postId);
    PostWithCommentDto getAllCommentsByPostId(long postId);
    public CommentDto getCommentById( long id);
}
