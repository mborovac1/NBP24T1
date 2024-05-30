package ba.unsa.etf.nbp24t1.projection;

import java.time.LocalDateTime;

public interface PriceReportProjection {
    String getMovieName();
    Double getMoviePrice();
    Long getUserId();
    LocalDateTime getAppointmentTime();
}