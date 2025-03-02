package com.MovieProject.MovieProject.Movie.RepReqUtils.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.MovieProject.MovieProject.Movie.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long>{
    //@Modifying
    //@Query(value = "SELECT * FROM Movies WHERE id = :id", nativeQuery = true)
    //Optional<Movie> findMovieById(Longe id);

    @Query(value = "SELECT * FROM movies_tb WHERE name = :name", nativeQuery = true)
    Optional<Movie> findMovieByName(String name);
}
