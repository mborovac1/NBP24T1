package ba.team1.ads_project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "MovieReview")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MovieReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Movie review text must exist.")
    @Column(name = "text", nullable = false)
    private String text;

    @NotEmpty(message = "Movie rating must exist.")
    @Positive(message = "Movie rating must be a positive number.")
    @Column(name = "rating", nullable = false)
    private Double rating;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "cinema_user_id")
    private CinemaUser cinemaUser;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "movie_id")
    private Movie movie;
}
