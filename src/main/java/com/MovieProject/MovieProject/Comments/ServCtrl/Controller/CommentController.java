package com.MovieProject.MovieProject.Comments.ServCtrl.Controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.MovieProject.MovieProject.Comments.Comment;
import com.MovieProject.MovieProject.Comments.RepReq.Requests.ReqSaveComment;
import com.MovieProject.MovieProject.Comments.ServCtrl.Service.CommentService;

import jakarta.transaction.Transactional;

@RestController
@Transactional
@RequestMapping("/comments")
public class CommentController {
    @Autowired
    private CommentService commentService;

    @GetMapping("/{id}")
    public ResponseEntity<List<Comment>> getAllMovieComments(@PathVariable Long id) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(commentService.getAllMovieComments(id));
    }

    @PostMapping("/newComment")
    public ResponseEntity<String> postNewMovieComment(@RequestBody ReqSaveComment reqSaveComment) throws Exception{
        commentService.postNewComment(reqSaveComment);
        return ResponseEntity.status(HttpStatus.OK).body("Saved");
    }
}
