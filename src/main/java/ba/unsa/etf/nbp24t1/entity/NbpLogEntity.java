package ba.unsa.etf.nbp24t1.entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Entity
@Table(name = "NBP_LOG", schema = "NBP")
@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
public class NbpLogEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "ID")
    private Long id;

    @Column(name = "ACTION_NAME", nullable = false)
    private String actionName;

    @Column(name = "TABLE_NAME", nullable = false)
    private String tableName;

    @Column(name = "DATE_TIME", nullable = false)
    private Timestamp createdAt;

    @Column(name = "DB_USER")
    private String dbUser;
}
