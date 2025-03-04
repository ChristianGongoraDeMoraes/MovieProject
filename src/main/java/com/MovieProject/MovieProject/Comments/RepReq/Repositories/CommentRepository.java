package com.MovieProject.MovieProject.Comments.RepReq.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import com.MovieProject.MovieProject.Comments.Comment;

public interface CommentRepository extends JpaRepository<Comment, Long>{
    
    @Query(value="SELECT * FROM comments_tb WHERE movie_id = :id", nativeQuery= true)
    List<Comment> getAllCommentsByMovieId(Long id);
}
