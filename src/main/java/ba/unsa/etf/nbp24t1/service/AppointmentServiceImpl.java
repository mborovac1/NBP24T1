package ba.unsa.etf.nbp24t1.service;
import ba.unsa.etf.nbp24t1.entity.AppointmentEntity;
import ba.unsa.etf.nbp24t1.repository.AppointmentRepository;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class AppointmentServiceImpl implements AppointmentService{
    private final AppointmentRepository appointmentRepository;

    @Override
    public List<AppointmentEntity> getAll() {
        return appointmentRepository.findAll();
    }

    @Override
    public ResponseEntity addAppointment(AppointmentEntity appointment) {
        appointmentRepository.save(appointment);

        JSONObject objekat = new JSONObject();
        try {
            objekat.put("message", "Appointment has been added successfully!");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<AppointmentEntity> request = new HttpEntity<>(appointment, headers);

        return new ResponseEntity(appointment, HttpStatus.CREATED);
    }

    @Override
    public List<AppointmentEntity> getAppointmentsByMovieId(int movieId) {
        return appointmentRepository.findByMovieId(movieId);
    }
}
