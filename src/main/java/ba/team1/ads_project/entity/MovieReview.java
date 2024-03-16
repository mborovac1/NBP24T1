package ba.team1.ads_project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MovieReview {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "text")
    private String text;

    @NotEmpty(message = "Movie rating must exist.")
    @Positive(message = "Movie rating must be a positive number.")
    @Column(name = "rating", nullable = false)
    private Double rating;
}
