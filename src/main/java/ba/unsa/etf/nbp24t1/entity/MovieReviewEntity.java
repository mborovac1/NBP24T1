package ba.unsa.etf.nbp24t1.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MOVIEREVIEW", schema = "NBP24T1")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MovieReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotBlank(message = "Movie review text must exist.")
    @Column(name = "TEXT", nullable = false)
    private String text;

    @NotEmpty(message = "Movie rating must exist.")
    @Positive(message = "Movie rating must be a positive number.")
    @Column(name = "RATING", nullable = false)
    private Double rating;

    @Column(name = "CINEMA_USER_ID")
    private Long cinemaUserId;

    @Column(name = "MOVIE_ID")
    private Long movieId;
}
