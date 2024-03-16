package ba.team1.ads_project.entity;

import com.fasterxml.jackson.annotation.JsonFormat;
import jakarta.persistence.*;
import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.PositiveOrZero;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.time.LocalDate;

@Entity
@Table(name = "Membership")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class Membership {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @FutureOrPresent(message = "Membership expire date must be in future.")
    @JsonFormat(pattern = "dd.MM.yyyy.")
    @Column(name = "expire_date")
    private LocalDate expireDate;

    @NotEmpty(message = "Membership discount must exist.")
    @PositiveOrZero(message = "Membership discount must be a non-negative number.")
    @Column(name = "discount", nullable = false)
    private Double discount;

    @NotBlank(message = "Membership type must exist.")
    @Column(name = "type", nullable = false)
    private String type;
}
