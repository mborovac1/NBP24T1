package ba.team1.ads_project.entity;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.Positive;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "Seat")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Seat {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @NotBlank(message = "Seat row must exist.")
    @Column(name = "seat_row", nullable = false, length = 2)
    private String row;

    @NotEmpty(message = "Seat number must exist.")
    @Positive(message = "Seat number must be a positive number.")
    @Column(name = "number", nullable = false)
    private Integer number;

    @NotBlank(message = "Seat type must exist.")
    @Column(name = "type", nullable = false, length = 50)
    private String type;

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "hall_id")
    private Hall hall;

    @JsonIgnore
    @OneToOne(mappedBy = "seat")
    private Ticket ticket;
}
