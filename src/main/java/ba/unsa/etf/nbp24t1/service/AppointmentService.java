package ba.unsa.etf.nbp24t1.service;
import ba.unsa.etf.nbp24t1.entity.AppointmentEntity;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface AppointmentService {
    List<AppointmentEntity> getAll();

    ResponseEntity addAppointment(AppointmentEntity appointment);

    List<AppointmentEntity> getAppointmentsByMovieId(int movieId);
}
