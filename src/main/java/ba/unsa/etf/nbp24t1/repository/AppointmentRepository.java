package ba.unsa.etf.nbp24t1.repository;


import ba.unsa.etf.nbp24t1.entity.AppointmentEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AppointmentRepository extends JpaRepository<AppointmentEntity, Long> {

    List<AppointmentEntity> findByMovieId(int movieId);

}
