package com.MovieProject.MovieProject.Movie.ServCtrl.Controllers;


import jakarta.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.MovieProject.MovieProject.Movie.Image;
import com.MovieProject.MovieProject.Movie.Movie;
import com.MovieProject.MovieProject.Movie.RepReqUtils.Repositories.ImageRepository;
import com.MovieProject.MovieProject.Movie.RepReqUtils.Repositories.MovieRepository;
import com.MovieProject.MovieProject.Movie.RepReqUtils.Requests.ImageUploadResponse;
import com.MovieProject.MovieProject.Movie.RepReqUtils.Requests.RequestMovieName;
import com.MovieProject.MovieProject.Movie.RepReqUtils.Utils.ImageUtility;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.IOException;
import java.util.Optional;

@RestController
@Transactional
@RequestMapping("/image")
public class ImageController {

    @Autowired
    ImageRepository imageRepository;

    @Autowired
    MovieRepository movieRepository;

    private RequestMovieName convertMovieName(String movieName) throws JsonProcessingException{
            ObjectMapper objectMapper = new ObjectMapper();
            return objectMapper.readValue(movieName , RequestMovieName.class);
    }

    @PostMapping("/upload/image")
    public ResponseEntity<ImageUploadResponse> uplaodImage(@RequestPart("image") MultipartFile file, @RequestPart("movie") String movieName)
            throws IOException , Exception{
        
        RequestMovieName movieReqName = convertMovieName(movieName);
        Movie movie = movieRepository.findMovieByName(movieReqName.getName()).orElseThrow(() -> new Exception("Movie Not found!"));
        
        //Deleting Posterior image
        if(imageRepository.findByMovieId(movie.getId()).isPresent()){
                imageRepository.deleteByMovieId(movie.getId());
        }
                
        imageRepository.save(Image.builder()
                .name(file.getOriginalFilename())
                .type(file.getContentType())
                .image(ImageUtility.compressImage(file.getBytes()))
                .movieImage(movie)
                .build());
        return ResponseEntity.status(HttpStatus.OK)
                .body(new ImageUploadResponse("Image uploaded successfully: " +
                        file.getOriginalFilename()));
    }

    @GetMapping("/get/image/info/{name}")
    public Image getImageDetails(@PathVariable String name) throws IOException {

        final Optional<Image> dbImage = imageRepository.findByName(name);

        return Image.builder()
                .name(dbImage.get().getName())
                .type(dbImage.get().getType())
                .image(ImageUtility.decompressImage(dbImage.get().getImage())).build();
    }
    
    

    @GetMapping("/get/image/{name}")
    public ResponseEntity<byte[]> getImage(@PathVariable String name) throws IOException {

        final Optional<Image> dbImage = imageRepository.findByName(name);

        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(dbImage.get().getType()))
                .body(ImageUtility.decompressImage(dbImage.get().getImage()));
    }

    @GetMapping("/get/image/movie/{name}")
    public ResponseEntity<byte[]> getImageByMovieName(@PathVariable String name) throws Exception {

        Movie movie = movieRepository.findMovieByName(name).orElseThrow(() -> new Exception("Movie Not found!"));

        final Image dbImage = imageRepository.findByMovieId(movie.getId()).orElseThrow(() -> new Exception("Image Not found!"));

        return ResponseEntity
                .ok()
                .contentType(MediaType.valueOf(dbImage.getType()))
                .body(ImageUtility.decompressImage(dbImage.getImage()));
    }
}