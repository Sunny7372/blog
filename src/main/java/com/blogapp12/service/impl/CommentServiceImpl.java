package com.blogapp12.service.impl;

import com.blogapp12.entity.Comment;
import com.blogapp12.entity.Post;
import com.blogapp12.exception.CommentNotFoundForCommentId;
import com.blogapp12.payload.CommentDto;
import com.blogapp12.payload.PostDto;
import com.blogapp12.payload.PostWithCommentDto;
import com.blogapp12.repository.CommentRepository;
import com.blogapp12.repository.PostRepository;
import com.blogapp12.service.CommentService;
import lombok.AllArgsConstructor;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
@AllArgsConstructor
public class CommentServiceImpl implements CommentService {
    private CommentRepository commentRepository;
    private ModelMapper modelMapper;
    private PostRepository postRepository;
    @Override
    public CommentDto createComment(CommentDto commentDto,long postId) {

        Optional<Post> byId = postRepository.findById(postId);
        Post post = byId.get();
        Comment comment = mapToEntity(commentDto);
        comment.setPost(post);
        Comment savedComment = commentRepository.save(comment);
        CommentDto dto = mapToDto(savedComment);
        return dto;


    }
    public CommentDto getCommentById(long id){
        Comment comment = commentRepository.findById(id).orElseThrow(
                ()->new CommentNotFoundForCommentId("Comment not exist for given comment Id:" +id)
        );
        return mapToDto(comment);
    }

    @Override
    public PostWithCommentDto getAllCommentsByPostId(long id) {
        Post post = postRepository.findById(id).get();
        PostDto dto=new PostDto();
        dto.setId(post.getId());
        dto.setDescription(post.getDescription());
        dto.setTitle(post.getTitle());
        dto.setContent(post.getContent());
        List<Comment> comments = commentRepository.findByPostId(id);
        List<CommentDto> commentDto = comments.stream().map(e -> mapToDto(e)).collect(Collectors.toList());
        PostWithCommentDto postWithCommentDto= new PostWithCommentDto();
        postWithCommentDto.setCommentDto(commentDto);
        postWithCommentDto.setPostDto(dto);

        return postWithCommentDto;
    }

    Comment mapToEntity(CommentDto commentDto){
         Comment comment = modelMapper.map(commentDto, Comment.class);
         return comment;

     }

     CommentDto mapToDto(Comment comment){
         CommentDto dto = modelMapper.map(comment, CommentDto.class);
         return dto;
     }


}
