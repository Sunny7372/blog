package com.blogapp12.exception;

public class CommentNotFoundForCommentId extends RuntimeException{
    public CommentNotFoundForCommentId(String message){
        super(message);
    }
}
