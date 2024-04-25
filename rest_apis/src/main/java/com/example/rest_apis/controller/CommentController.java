package com.example.rest_apis.controller;


import com.example.rest_apis.payloads.ApiResponse;
import com.example.rest_apis.payloads.CommentDto;
import com.example.rest_apis.services.CommentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/")
public class CommentController {


    @Autowired
    private CommentService commentService;

    @PostMapping("/post/{postId}/comments")
    public ResponseEntity<CommentDto> createComment(@RequestBody CommentDto commentDto, @PathVariable("postId") Integer postId){

        CommentDto createComment = this.commentService.createComment(commentDto, postId);

        return new ResponseEntity<CommentDto>(createComment,HttpStatus.CREATED);

    }


    @DeleteMapping("/delete/comments/{commentId}")
    public ResponseEntity<ApiResponse> deleteComment(@PathVariable("commentId") Integer commentId){

        this.commentService.deleteComment(commentId);

        return new ResponseEntity<ApiResponse>(new ApiResponse("Successfully deleted !!",true),HttpStatus.OK);

    }




}

