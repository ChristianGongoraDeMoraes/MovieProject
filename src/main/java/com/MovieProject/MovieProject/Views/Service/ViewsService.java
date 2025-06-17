package com.MovieProject.MovieProject.Views.Service;

import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.MovieProject.MovieProject.Movie.RepReqUtils.Repositories.MovieRepository;
import com.MovieProject.MovieProject.Views.Views;
import com.MovieProject.MovieProject.Views.Repository.ViewsRepository;
import com.MovieProject.MovieProject.Views.dto.SaveViewRequest;

@Service
public class ViewsService {
    @Autowired
    private ViewsRepository viewsRepository;
    @Autowired
    private MovieRepository movieRepository;

    public List<Views> getAll(){
        return viewsRepository.findAll();
    }

    public Views saveView(SaveViewRequest request) throws Exception{
        var movie = movieRepository.findById(request.movieId()).orElseThrow(() -> new Exception("Movie doesn't exist"));
        var existView = viewsRepository.findViewsByMovieId(request.movieId()).isPresent();
        if(existView == false){
            var viwer = Views
                    .builder()
                    .views(1)
                    .movieViews(movie)
                    .build();
            viewsRepository.save(viwer);
            return viwer;
        }
        var valueExistView = viewsRepository.findViewsByMovieId(request.movieId()).get();
        var viwer = Views
            .builder()
            .views(valueExistView.getViews() + 1)
            .movieViews(movie)
            .build();
        viewsRepository.DeleteViewsByMovieId(request.movieId());

        viewsRepository.save(viwer);

        return viwer;
    }

    public List<Views> getTopTen() {
        return viewsRepository.getTopTen();
    }
}
