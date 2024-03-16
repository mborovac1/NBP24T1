package ba.team1.ads_project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
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
public class City {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotEmpty(message = "City name must exist.")
    @Column(name = "name", nullable = false)
    private String name;

    @NotEmpty(message = "City postcode must exist.")
    @PositiveOrZero(message = "City postcode must be a non-negative number.")
    @Column(name = "postcode", nullable = false)
    private Long postcode;
}
