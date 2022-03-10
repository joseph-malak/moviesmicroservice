package com.example.moviecatalogservice.resources;


import com.example.moviecatalogservice.models.CatalogItem;
import com.example.moviecatalogservice.models.Movies;
import com.example.moviecatalogservice.models.Rating;
import com.example.moviecatalogservice.models.UserRating;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import org.springframework.web.reactive.function.client.WebClient;

import java.lang.reflect.ParameterizedType;
import java.nio.file.WatchEvent;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/catalog")
public class MovieCatalogResource {

    @Autowired
    private RestTemplate restTemplate;//this will tell spring to look for a bean that produces resttemplate, excecutes it then injects it

    @Autowired
    private WebClient.Builder webClientBuilder;

    @RequestMapping("/{userId}")
    public List<CatalogItem> getCatalog(@PathVariable("userId") String userId){



        RestTemplate restTemplate = new RestTemplate(); //has methods
        // get all rated movie IDs /
        // this list should come from the api
        List<Rating> ratings = restTemplate.getForObject("http://localhost:8083/ratingsdata/users/" + userId, UserRating.class);
        //looping
        //hard code return
        return ratings.getUserRating().stream().map(rating ->{

                    // For each one, call movie info service and get details
                   Movies movie = restTemplate.getForObject("http://localhost:8083/movies/" + rating.getMovieId() /*get that specific id from each movie*/, Movies.class);// getForObject creates an instance of a class and give properties to it, gives fully formed obj

                   return new CatalogItem(movie.getName(), "things happen" , rating.getRating());

//                  Movies movie = webClientBuilder.build()
//                           .get()
//                           .uri("http://localhost:8083/movies/" + rating.getMovieId())
//                           .retrieve()
//                           .bodyToMono(Movies.class);
//                            .block();


        })
                .collect(Collectors.toList());






        // Put them all together

    }


}
