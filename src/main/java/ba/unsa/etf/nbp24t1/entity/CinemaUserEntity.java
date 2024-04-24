package ba.unsa.etf.nbp24t1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "CINEMAUSER", schema = "NBP24T1")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class CinemaUserEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "user_id")
    private Long userId;

    @Column(name = "membership_id")
    private Long membershipId;
}
