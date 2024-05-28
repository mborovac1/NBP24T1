package ba.unsa.etf.nbp24t1.controller;
import ba.unsa.etf.nbp24t1.entity.AppointmentEntity;
import ba.unsa.etf.nbp24t1.service.AppointmentService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/appointments")
@CrossOrigin
@RestController
public class AppointmentController {
    private final AppointmentService appointmentService;

    @GetMapping("/")
    public List<AppointmentEntity> getAll() {
        return appointmentService.getAll();
    }

    @PostMapping("/add")
    public ResponseEntity addAppointment(@RequestBody AppointmentEntity appointment) {
        return appointmentService.addAppointment(appointment);
    }

    @GetMapping("/{movieId}")
    public ResponseEntity getAppointmentsByMovieId(@PathVariable int movieId){
        List<AppointmentEntity> appointments = appointmentService.getAppointmentsByMovieId(movieId);

        if (appointments.isEmpty()) {
            return ResponseEntity.noContent().build();
        }

        return ResponseEntity.ok(appointments);
    }
}
