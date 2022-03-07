package com.example.movieinfoservice.model;


import com.example.movieinfoservice.resources.Movies;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/movies")
public class MovieResources {

        @RequestMapping("/{movieId}")
        public Movies getMovieInfo(@PathVariable("movieId") int movieId){
            return new Movies(movieId, "test name" );
        }

}
