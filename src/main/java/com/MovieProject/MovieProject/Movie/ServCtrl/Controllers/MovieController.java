package com.MovieProject.MovieProject.Movie.ServCtrl.Controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.repository.query.Param;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.MovieProject.MovieProject.Movie.Movie;
import com.MovieProject.MovieProject.Movie.RepReqUtils.Requests.MovieSaveRequest;
import com.MovieProject.MovieProject.Movie.ServCtrl.Services.MovieService;

import jakarta.transaction.Transactional;

@RestController
@RequestMapping("/Api/Movie")
@Transactional
public class MovieController {
    @Autowired
    private MovieService movieService;

    @GetMapping
    public ResponseEntity<List<Movie>> getAllMovies(
        @RequestParam(value="pageNo",defaultValue = "0", required = false ) int pageNo , 
        @RequestParam(value="pageSize",defaultValue = "24", required = false ) int pageSize
        ){
        return ResponseEntity.status(HttpStatus.OK).body(movieService.getAllMovies(pageNo, pageSize));
    }

    @GetMapping("/{id}")
    public ResponseEntity<Movie> getOrderByUserId(@PathVariable Long id) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(movieService.getMovieById(id));
    }
    
    @GetMapping("/searchLike/{name}")
    public ResponseEntity<List<Movie>> getMoviesByNameLike(
        @PathVariable String name,
        @RequestParam(value="pageNo",defaultValue = "0", required = false ) int pageNo , 
        @RequestParam(value="pageSize",defaultValue = "24", required = false ) int pageSize) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(movieService.getMovieByNameLike(name, pageNo, pageSize));
    }

    @PostMapping
    public ResponseEntity<Movie> makeNewOrder(@RequestBody MovieSaveRequest movieReq){
        return ResponseEntity.status(HttpStatus.CREATED).body(movieService.newMovie(movieReq));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<String> deleteUser(@PathVariable Long id) throws Exception{
        return ResponseEntity.status(HttpStatus.OK).body(movieService.deleteMovieById(id));
    }
}