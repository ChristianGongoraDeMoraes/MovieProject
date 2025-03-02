package com.MovieProject.MovieProject.Movie.ServCtrl.Services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MovieProject.MovieProject.Movie.Movie;
import com.MovieProject.MovieProject.Movie.RepReqUtils.Repositories.MovieRepository;
import com.MovieProject.MovieProject.Movie.RepReqUtils.Requests.MovieSaveRequest;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    public List<Movie> getAllMovies(){
        return movieRepository.findAll();
    }

    public Movie getMovieById(Long id) throws Exception{
        return movieRepository.findById(id).orElseThrow(() -> new Exception("Movie Not found!"));
    }

    public List<Movie> getMovieByNameLike(String name) throws Exception{
        return movieRepository.findMoviesByNameLike(name).orElseThrow(() -> new Exception("Movie Not found!"));
    }

    public Movie newMovie(MovieSaveRequest movieReq){
       Movie movie = Movie.builder()
            .name(movieReq.name())
            .rate(movieReq.rate())
            .description(movieReq.description())
            .build();
        return movieRepository.save(movie);
    }

    public String deleteMovie(Long id) throws Exception{
        if(movieRepository.existsById(id)){
            movieRepository.deleteById(id);
            return "Movie Deleted";
        }else{
            throw new Exception("Movie Not found!");
        }
    }
}