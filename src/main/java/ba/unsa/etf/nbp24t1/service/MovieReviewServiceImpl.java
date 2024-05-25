package ba.unsa.etf.nbp24t1.service;

import ba.unsa.etf.nbp24t1.entity.MovieReviewEntity;
import ba.unsa.etf.nbp24t1.repository.MovieReviewRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class MovieReviewServiceImpl implements MovieReviewService {

    private final MovieReviewRepository movieReviewRepository;

    @Override
    public List<MovieReviewEntity> getAll() {
        return movieReviewRepository.findAll();
    }

    @Override
    public ResponseEntity<?> addMovieReview(MovieReviewEntity movieReview) {
        List<MovieReviewEntity> existingReviews = movieReviewRepository.findByCinemaUserIdAndMovieId(
                movieReview.getCinemaUserId(), movieReview.getMovieId());

        if (!existingReviews.isEmpty()) {
            JSONObject response = new JSONObject();
            try {
                response.put("message", "User has already left a review for this movie.");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return new ResponseEntity<>(response.toString(), headers, HttpStatus.BAD_REQUEST);
        }

        movieReviewRepository.save(movieReview);

        JSONObject response = new JSONObject();
        try {
            response.put("message", "Movie review has been added successfully!");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        return new ResponseEntity<>(response.toString(), headers, HttpStatus.CREATED);
    }
}
