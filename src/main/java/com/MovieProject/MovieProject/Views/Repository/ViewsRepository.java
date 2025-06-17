package com.MovieProject.MovieProject.Views.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.MovieProject.MovieProject.Movie.Movie;
import com.MovieProject.MovieProject.Views.Views;

import jakarta.transaction.Transactional;

public interface ViewsRepository extends JpaRepository<Views, Long>{
    @Query(value = "SELECT * FROM views_tb WHERE movie_id = :id", nativeQuery = true)
    Optional<Views> findViewsByMovieId(Long id);

    @Modifying
    @Transactional
    @Query(value = "Delete FROM views_tb WHERE movie_id = :id", nativeQuery = true)
    void DeleteViewsByMovieId(Long id);

    @Query(value = "select * FROM views_tb order by views desc limit 10", nativeQuery = true)
    List<Views> getTopTen();
}
