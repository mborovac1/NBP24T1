package ba.team1.ads_project.entity;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
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
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotEmpty(message = "Seat row must exist.")
    @Positive(message = "Seat row must be a positive number.")
    @Column(name = "seat_row", nullable = false)
    private Double row;

    @NotEmpty(message = "Seat number must exist.")
    @Positive(message = "Seat number must be a positive number.")
    @Column(name = "number", nullable = false)
    private Integer number;

    @NotEmpty(message = "Seat type must exist.")
    @Column(name = "type", nullable = false)
    private String type;
}
