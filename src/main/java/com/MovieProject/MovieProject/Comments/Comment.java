package com.MovieProject.MovieProject.Comments;

import java.io.Serializable;

import com.MovieProject.MovieProject.Movie.Movie;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@Table(name = "comments_tb")
public class Comment implements Serializable{
    private static final long serialVersionUID = 1L;

    @Id
    @GeneratedValue
    private Long id;
    
    @Column(unique = true, nullable = false)
    private String body;

   @ManyToOne
   @JoinColumn(name = "movie_id")
   private Movie movieComment;
}