package com.MovieProject.MovieProject.Comments.ServCtrl.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MovieProject.MovieProject.Comments.Comment;
import com.MovieProject.MovieProject.Comments.RepReq.Repositories.CommentRepository;
import com.MovieProject.MovieProject.Comments.RepReq.Requests.ReqSaveComment;
import com.MovieProject.MovieProject.Movie.RepReqUtils.Repositories.MovieRepository;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class CommentService {
    @Autowired
    private CommentRepository commentRepository;

    @Autowired
    private MovieRepository movieRepository;

    public List<Comment> getAllMovieComments(Long id){
        return commentRepository.getAllCommentsByMovieId(id);
    }   

    public void postNewComment(ReqSaveComment reqComment){
        Comment comment = Comment.builder()
            .body(reqComment.body())
            .movieComment(movieRepository.findMovieById(reqComment.movieId()).get())
            .build();
        
        commentRepository.save(comment);
    }
}
