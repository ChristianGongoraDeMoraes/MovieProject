package com.MovieProject.MovieProject.Movie.RepReqUtils.Repositories;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.MovieProject.MovieProject.Movie.Image;



public interface ImageRepository extends JpaRepository<Image, Long> {
	Optional<Image> findByName(String name);

	@Query(value="Select * From Movies_images where movie_id = :id", nativeQuery = true)
	Optional<Image> findByMovieId(Long id);

	@Modifying
	@Query(value="Delete From Movies_images where movie_id = :id", nativeQuery = true)
	Optional<Image> deleteByMovieId(Long id);
}
