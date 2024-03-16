package ba.team1.ads_project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import jakarta.validation.constraints.PositiveOrZero;
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
}
