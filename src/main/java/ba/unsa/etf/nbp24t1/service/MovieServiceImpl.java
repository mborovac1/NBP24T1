package ba.unsa.etf.nbp24t1.service;

import ba.unsa.etf.nbp24t1.entity.MovieEntity;
import ba.unsa.etf.nbp24t1.entity.MovieGenreEntity;
import ba.unsa.etf.nbp24t1.exception.NotFoundException;
import ba.unsa.etf.nbp24t1.repository.MovieGenreRepository;
import ba.unsa.etf.nbp24t1.repository.MovieRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    private final MovieGenreRepository movieGenreRepository;

    @Override
    public List<MovieEntity> getAll() {
        return movieRepository.findAll();
    }

    @Override
    public ResponseEntity deleteMovie(int id) {
        if (movieRepository.existsById((long) id)) {
            JSONObject objekat = new JSONObject();
            Optional<MovieEntity> optionalMovie = movieRepository.findById((long) id);
            if (!optionalMovie.isPresent()) {
                throw new NotFoundException("Movie does not exist.");
            }
            MovieEntity film = optionalMovie.get();

            List<MovieGenreEntity> movieGenres = movieGenreRepository.findByMovieId(film.getId());
            movieGenreRepository.deleteAll(movieGenres);

            movieRepository.deleteById((long) id);
            try {
                objekat.put("message", "Movie deleted successfully!");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return new ResponseEntity(objekat.toString(), HttpStatus.OK);
        } else {
            throw new NotFoundException("Movie with Id " + id + " does not exist!");
        }
    }

    @Override
    public ResponseEntity addMovie(MovieEntity movie) {
        movieRepository.save(movie);

        JSONObject objekat = new JSONObject();
        try {
            objekat.put("message", "Movie has been added successfully!");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<MovieEntity> request = new HttpEntity<>(movie, headers);


        return new ResponseEntity(movie, HttpStatus.CREATED);
    }
}
