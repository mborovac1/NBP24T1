package ba.team1.ads_project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "Hall")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Hall {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotEmpty(message = "Hall number must exist.")
    @Positive(message = "Hall number must be a positive number.")
    @Column(name = "number", nullable = false, unique = true)
    private Integer number;

    @NotEmpty(message = "Hall capacity must exist.")
    @PositiveOrZero(message = "Hall capacity must be a non-negative number.")
    private Integer capacity;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "cinema_id")
    private Cinema cinema;

    @OneToMany(mappedBy = "hall")
    private List<Seat> seats = new ArrayList<>();
}
