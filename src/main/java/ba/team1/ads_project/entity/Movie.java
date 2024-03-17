package ba.team1.ads_project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Movie")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Movie {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
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

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.MERGE)
    @JoinTable(
            name = "MovieGenre",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "genre_id")
    )
    private List<Genre> genres = new ArrayList<>();

    @ManyToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinTable(
            name = "MovieCinema",
            joinColumns = @JoinColumn(name = "movie_id"),
            inverseJoinColumns = @JoinColumn(name = "cinema_id")
    )
    private List<Cinema> cinemas = new ArrayList<>();

    @OneToMany(mappedBy = "movie")
    private List<Ticket> tickets = new ArrayList<>();

    @OneToMany(mappedBy = "movie")
    private List<MovieReview> reviews = new ArrayList<>();
}
