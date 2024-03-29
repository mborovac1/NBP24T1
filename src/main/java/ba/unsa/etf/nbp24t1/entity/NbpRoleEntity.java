package ba.unsa.etf.nbp24t1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "NBP_ROLE", schema = "NBP")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class NbpRoleEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "NAME", length = 50, nullable = false, unique = true)
    private String name;
}
