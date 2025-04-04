package ba.unsa.etf.nbp24t1.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDateTime;

@Entity
@Table(name = "MOVIE", schema = "NBP24T1")
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
    @Column(name = "NAME", nullable = false, length = 50)
    private String name;

    @NotEmpty(message = "Movie duration must exist.")
    @Column(name = "DURATION", nullable = false)
    private Integer duration;

    @NotBlank(message = "Movie description must exist.")
    @Column(name = "DESCRIPTION", nullable = false)
    private String description;

    @Column(name = "POSTER_PATH", nullable = false)
    private String posterPath;

    @NotEmpty(message = "Movie price must exist.")
    @Column(name = "PRICE", nullable = false)
    private Double price;

    @NotEmpty(message = "Created at must exist.")
    @Column(name = "CREATED_AT", nullable = false)
    private LocalDateTime createdAt;

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }
}
