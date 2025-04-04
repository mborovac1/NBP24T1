package ba.unsa.etf.nbp24t1.entity;

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
@Table(name = "MEMBERSHIP", schema = "NBP24T1")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class MembershipEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @NotEmpty(message = "Membership discount must exist.")
    @PositiveOrZero(message = "Membership discount must be a non-negative number.")
    @Column(name = "DISCOUNT", nullable = false)
    private Double discount;

    @FutureOrPresent(message = "Membership expire date must be in future.")
    @JsonFormat(pattern = "dd.MM.yyyy.")
    @Column(name = "EXPIRY_DATE")
    private LocalDate expiryDate;

    @NotBlank(message = "Membership type must exist.")
    @Enumerated(EnumType.STRING)
    @Column(name = "TYPE", nullable = false, length = 50)
    private MembershipType type;
}
