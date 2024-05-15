package ba.unsa.etf.nbp24t1.service;

import ba.unsa.etf.nbp24t1.entity.MovieGenreEntity;
import ba.unsa.etf.nbp24t1.repository.MovieGenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@RequiredArgsConstructor
@Service
public class MovieGenreServiceImpl implements MovieGenreService {

    private final MovieGenreRepository movieGenreRepository;

    @Override
    public List<MovieGenreEntity> getAll() {
        return movieGenreRepository.findAll();
    }

    @Override
    public ResponseEntity createMovieGenre(List<MovieGenreEntity> movieGenres) {
        try {
            List<MovieGenreEntity> savedMovieGenres = new ArrayList<>();
            for (MovieGenreEntity movieGenre : movieGenres) {
                savedMovieGenres.add(movieGenreRepository.save(movieGenre));
            }
            return ResponseEntity.status(HttpStatus.CREATED).body(savedMovieGenres);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Failed to create movie genre: " + e.getMessage());
        }
    }
}
