package com.example.rest_apis.services.impl;

import com.example.rest_apis.entity.Comment;
import com.example.rest_apis.entity.Post;
import com.example.rest_apis.exceptions.ResourceNotFoundException;
import com.example.rest_apis.payloads.CommentDto;
import com.example.rest_apis.repository.CommentRepository;
import com.example.rest_apis.repository.PostRepository;
import com.example.rest_apis.services.CommentService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;

public class CommentServiceImpl implements CommentService {

    @Autowired
    PostRepository postRepository;

    @Autowired
    CommentRepository commentRepository;

    @Autowired
    private ModelMapper modelMapper;

    @Override
    public CommentDto createComment(CommentDto commentDto, Integer postId) {

        Post post = this.postRepository.findById(postId).orElseThrow(()-> new ResourceNotFoundException("post", "post id", postId));

        Comment comment = this.modelMapper.map(commentDto, Comment.class);

        comment.setPost(post);

        Comment savedComment = this.commentRepository.save(comment);


        return this.modelMapper.map(savedComment, CommentDto.class);
    }

    @Override
    public void deleteComment(Integer commentId) {

        Comment comment = this.commentRepository.findById(commentId).orElseThrow(()->new ResourceNotFoundException("comment", "comment id", commentId));

        this.commentRepository.delete(comment);
    }


}
