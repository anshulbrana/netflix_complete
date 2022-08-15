package parent.controller;

import parent.datamodel.Movie;
import parent.exceptions.BadRequestAlertException;
import parent.exceptions.ResourceNotFoundException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import parent.repo.MovieRepo;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.List;

@RestController
@RequestMapping("/netflix")
public class MovieController {

    @Autowired
    private MovieRepo movieRepo;

    @PostMapping("/movies")
    public ResponseEntity<Movie> createMovie(@RequestBody Movie movie) throws URISyntaxException {
        if (movie.getId() != null) {
            throw new BadRequestAlertException("A new movie cannot already have an ID");
        }
        Movie result = movieRepo.createMovie(movie);
        return ResponseEntity.created(new URI("/netflix/movies/" + result.getId()))
                .body(result);
    }

    @GetMapping("/movies")
    public List<Movie> getAllMovies() {
        return movieRepo.getMovies();
    }


    @GetMapping("/movies/{id}")
    public ResponseEntity<Movie> getMovie(@PathVariable Long id) {
        Movie movie = movieRepo.getById(id);
        if(!movie.getId().equals(id)){
            throw new ResourceNotFoundException("Not found movie with id = " + id);
        }
        return new ResponseEntity<>(movie, HttpStatus.OK);
    }

    @GetMapping("/get-movie/{category}")
    public List<Movie> getMoviesCatergory(@PathVariable String category) {
        return movieRepo.getbyCategory(category);
    }
//    public ResponseEntity<Movie> getMovieCategory(@PathVariable String category) {
//        Movie movie = movieRepo.getbyCategory(category);
//        if(!movie.getCategory().equals(category)){
//            throw new ResourceNotFoundException("Not found movie with this category = " + category);
//        }
//        return new ResponseEntity<>(movie, HttpStatus.OK);
//    }



    @DeleteMapping("/movies/{id}")
    public ResponseEntity<Void> deleteMovie(@PathVariable Long id) {
        Movie movie = movieRepo.getById(id);
        if(!movie.getId().equals(id)){
            throw new ResourceNotFoundException("Not found Role with id = " + id);
        }
        else{
            movieRepo.deleteByName(movie.getTitle());
        }
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }


}

