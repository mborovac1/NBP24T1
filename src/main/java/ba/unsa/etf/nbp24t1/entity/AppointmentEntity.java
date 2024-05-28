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
@Table(name = "APPOINTMENT", schema = "NBP24T1")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class AppointmentEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotEmpty(message = "Start time must exist.")
    @Column(name = "start_time")
    private LocalDateTime startTime;

    @NotEmpty(message = "End time must exist.")
    @Column(name = "end_time")
    private LocalDateTime endTime;

    @NotBlank(message = "Movie id must exist.")
    @Column(name = "movie_id")
    private Long movieId;

    @NotBlank(message = "Hall id must exist.")
    @Column(name = "hall_id")
    private Long hallId;


}
