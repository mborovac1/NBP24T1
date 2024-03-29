package ba.team1.ads_project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "City", schema = "NBP24T1")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CityEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotBlank(message = "City name must exist.")
    @Column(name = "name", nullable = false, length = 50)
    private String name;

    @NotEmpty(message = "City postcode must exist.")
    @PositiveOrZero(message = "City postcode must be a non-negative number.")
    @Column(name = "postcode", nullable = false)
    private Integer postcode;
}
