package ba.team1.ads_project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Cinema")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Cinema {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Cinema name must exist.")
    @Column(name = "name", nullable = false)
    private String name;

    @JsonIgnore
    @OneToOne(fetch = FetchType.LAZY, cascade = CascadeType.ALL)
    @JoinColumn(name = "address_id")
    private Address address;

    @OneToMany(mappedBy = "cinema")
    private List<CinemaReview> reviews = new ArrayList<>();

    @JsonIgnore
    @ManyToMany(fetch = FetchType.LAZY, mappedBy = "cinemas")
    private List<Movie> movies = new ArrayList<>();

    @OneToMany(mappedBy = "cinema")
    private List<Hall> halls = new ArrayList<>();
}
