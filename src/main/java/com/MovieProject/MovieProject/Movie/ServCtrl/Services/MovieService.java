package com.MovieProject.MovieProject.Movie.ServCtrl.Services;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;

import com.MovieProject.MovieProject.Movie.Movie;
import com.MovieProject.MovieProject.Movie.RepReqUtils.Repositories.ImageRepository;
import com.MovieProject.MovieProject.Movie.RepReqUtils.Repositories.MovieRepository;
import com.MovieProject.MovieProject.Movie.RepReqUtils.Requests.MovieSaveRequest;

import jakarta.transaction.Transactional;

@Service
@Transactional
public class MovieService {
    @Autowired
    private MovieRepository movieRepository;

    @Autowired 
    ImageRepository imageRepository;

    public List<Movie> getAllMovies(int pageNo, int pageSize){
        PageRequest pageable = PageRequest.of(pageNo, pageSize);

        Page<Movie> movies =  movieRepository.findAll(pageable); 

        return movies.stream().map(p -> {
            return new Movie(p.getId(), p.getName(), p.getRate(), p.getDescription());
        }).collect(Collectors.toList());
    }

    public Movie getMovieById(Long id) throws Exception{
        return movieRepository.findById(id).orElseThrow(() -> new Exception("Movie Not found!"));
    }

    public List<Movie> getMovieByNameLike(String name, int pageNo, int pageSize) throws Exception{
        int offSet = pageNo * pageSize;
        return movieRepository.findMoviesByNameLike(name, offSet, pageSize).orElseThrow(() -> new Exception("Movie Not found!"));
    }

    public Movie newMovie(MovieSaveRequest movieReq){
       Movie movie = Movie.builder()
            .name(movieReq.name())
            .rate(movieReq.rate())
            .description(movieReq.description())
            .build();
        return movieRepository.save(movie);
    }

    public String deleteMovieById(Long id) throws Exception{
        if(movieRepository.existsById(id)){
            imageRepository.deleteByMovieId(id);
            movieRepository.deleteMovieById(id);
            return "Movie Deleted";
        }else{
            throw new Exception("Movie Not found!");
        }
    }
}