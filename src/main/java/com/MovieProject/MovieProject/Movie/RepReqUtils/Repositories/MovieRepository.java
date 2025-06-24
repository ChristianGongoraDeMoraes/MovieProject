package com.MovieProject.MovieProject.Movie.RepReqUtils.Repositories;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.MovieProject.MovieProject.Movie.Movie;

public interface MovieRepository extends JpaRepository<Movie, Long>{
    
    @Query(value = "SELECT * FROM movies_tb WHERE id = :id", nativeQuery = true)
    Optional<Movie> findMovieById(Long id);

    @Query(value = "SELECT * FROM movies_tb WHERE name = :name", nativeQuery = true)
    Optional<Movie> findMovieByName(String name);

    @Query(value = "SELECT * FROM movies_tb WHERE name ILIKE concat('%', concat(:name, '%')) LIMIT :pageSize OFFSET :offSet", nativeQuery = true)
    Optional<List<Movie>> findMoviesByNameLike(String name, int offSet, int pageSize);

    @Modifying
    @Query(value = "DELETE FROM movies_tb WHERE id = :id", nativeQuery = true)
    void deleteMovieById(Long id);


}
