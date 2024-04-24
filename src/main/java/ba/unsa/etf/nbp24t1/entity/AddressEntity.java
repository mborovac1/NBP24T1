package ba.unsa.etf.nbp24t1.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "ADDRESS", schema = "NBP24T1")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AddressEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotBlank(message = "Address name must exist.")
    @Column(name = "name", nullable = false)
    private String name;

    @Column(name = "city_id")
    private Long cityId;
}
