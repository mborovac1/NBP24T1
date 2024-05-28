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
@Table(name = "CINEMAREVIEW", schema = "NBP24T1")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CinemaReviewEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotBlank(message = "Cinema review text must exist.")
    @Column(name = "TEXT", nullable = false)
    private String text;

    @NotEmpty(message = "Cinema rating must exist.")
    @Positive(message = "Cinema rating must be a positive number.")
    @Column(name = "RATING", nullable = false)
    private Double rating;

    @Column(name = "CINEMA_USER_ID")
    private Long cinemaUserId;

    @Column(name = "CINEMA_ID")
    private Long cinemaId;
}
