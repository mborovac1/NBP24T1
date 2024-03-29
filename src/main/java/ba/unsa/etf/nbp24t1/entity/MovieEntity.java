package ba.unsa.etf.nbp24t1.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Movie", schema = "NBP24T1")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MovieEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotBlank(message = "Movie name must exist.")
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @NotEmpty(message = "Movie duration must exist.")
    @Column(name = "duration", nullable = false)
    private Integer duration;

    @NotBlank(message = "Movie description must exist.")
    @Column(name = "description", nullable = false)
    private String description;

    @Column(name = "poster_path", nullable = false)
    private String posterPath;
}
