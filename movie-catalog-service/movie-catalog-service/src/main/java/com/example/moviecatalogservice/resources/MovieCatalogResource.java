package com.example.moviecatalogservice.resources;


import com.example.moviecatalogservice.models.CatalogItem;
import com.example.moviecatalogservice.models.Movies;
import com.example.moviecatalogservice.models.Rating;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){

        RestTemplate restTemplate = new RestTemplate(); //has methods
        // get all rated movie IDs /
        // this list should come from the api
        List<Rating> ratings = Arrays.asList(

                new Rating("1234", 5),
                new Rating("5678", 6)
        );
        //looping
        //hard code return
        return ratings.stream().map(rating ->{
                //calls are synchronous but you can make them asynchronous
                   Movies movie = restTemplate.getForObject("http://localhost:8081/movies/" + rating.getMovieId() /*get that specific id from each movie*/, Movies.class);// getForObject creates an instance of a class and give properties to it, gives fully formed obj

                   return new CatalogItem(movie.getName(), "things happen" , rating.getRating());

        })
                .collect(Collectors.toList());




        // For each one, call movie info service and get details

        // Put them all together

    }


}
