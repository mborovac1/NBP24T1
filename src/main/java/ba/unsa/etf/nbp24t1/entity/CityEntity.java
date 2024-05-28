package ba.unsa.etf.nbp24t1.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CITY", schema = "NBP24T1")
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
    @Column(name = "NAME", nullable = false, length = 50)
    private String name;

    @NotEmpty(message = "City postcode must exist.")
    @PositiveOrZero(message = "City postcode must be a non-negative number.")
    @Column(name = "POSTCODE", nullable = false)
    private Integer postcode;
}
