package ba.team1.ads_project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
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
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotEmpty(message = "Movie name must exist.")
    @Column(name = "name", nullable = false)
    private String name;

    @NotEmpty(message = "Movie duration must exist.")
    @Column(name = "duration", nullable = false)
    private Integer duration;

    @NotEmpty(message = "Movie description must exist.")
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "poster_path")
    private String posterPath;
}
