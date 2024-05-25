package ba.unsa.etf.nbp24t1.service;

import ba.unsa.etf.nbp24t1.entity.CinemaReviewEntity;
import ba.unsa.etf.nbp24t1.entity.MovieEntity;
import ba.unsa.etf.nbp24t1.repository.CinemaReviewRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class CinemaReviewServiceImpl implements CinemaReviewService {

    private final CinemaReviewRepository cinemaReviewRepository;

    @Override
    public List<CinemaReviewEntity> getAll() {
        return cinemaReviewRepository.findAll();
    }

    @Override
    public ResponseEntity addCinemaReview(CinemaReviewEntity cinemaReview) {
        // Check if a review already exists for the user
        List<CinemaReviewEntity> existingReviews = cinemaReviewRepository.findByCinemaUserId(cinemaReview.getCinemaUserId());
        if (!existingReviews.isEmpty()) {
            // User has already left a review
            JSONObject objekat = new JSONObject();
            try {
                objekat.put("message", "User has already left a review for this cinema.");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            HttpHeaders headers = new HttpHeaders();
            headers.setContentType(MediaType.APPLICATION_JSON);
            return new ResponseEntity<>(objekat.toString(), headers, HttpStatus.BAD_REQUEST);
        }

        // No existing review for the user, proceed to save the new review
        cinemaReviewRepository.save(cinemaReview);

        // Return success message
        JSONObject objekat = new JSONObject();
        try {
            objekat.put("message", "Cinema review has been added successfully!");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<CinemaReviewEntity> request = new HttpEntity<>(cinemaReview, headers);
        return new ResponseEntity<>(cinemaReview, headers, HttpStatus.CREATED);
    }


    @Override
    public ResponseEntity deleteCinemaReview(int id) {
        return null;
    }
}
