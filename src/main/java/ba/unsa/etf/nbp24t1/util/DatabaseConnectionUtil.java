package ba.unsa.etf.nbp24t1.util;

import ba.unsa.etf.nbp24t1.repository.*;
import ba.unsa.etf.nbp24t1.repository.auth.TokenRepository;
import ba.unsa.etf.nbp24t1.repository.auth.UserRepository;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;

@Slf4j
@RequiredArgsConstructor
@Component
public class DatabaseConnectionUtil {

    private final AddressRepository addressRepository;
    private final CinemaRepository cinemaRepository;
    private final CinemaReviewRepository cinemaReviewRepository;
    private final CinemaUserRepository cinemaUserRepository;
    private final CityRepository cityRepository;
    private final GenreRepository genreRepository;
    private final HallRepository hallRepository;
    private final MembershipRepository membershipRepository;
    private final MovieCinemaRepository movieCinemaRepository;
    private final MovieGenreRepository movieGenreRepository;
    private final MovieRepository movieRepository;
    private final MovieReviewRepository movieReviewRepository;
    private final NbpLogRepository nbpLogRepository;
    private final NbpRoleRepository nbpRoleRepository;
    private final NbpUserRepository nbpUserRepository;
    private final SeatRepository seatRepository;
    private final TicketRepository ticketRepository;
    private final UserRepository userRepository;
    private final TokenRepository tokenRepository;

    @PostConstruct
    private void fetchDatabaseData() {
        log.info("Addresses: " + addressRepository.findAll());
        log.info("Cinemas: " + cinemaRepository.findAll());
        log.info("Cinema reviews:: " + cinemaReviewRepository.findAll());
        log.info("Cinema users: " + cinemaUserRepository.findAll());
        log.info("Cities: " + cityRepository.findAll());
        log.info("Genres: " + genreRepository.findAll());
        log.info("Halls: " + hallRepository.findAll());
        log.info("Memberships: " + membershipRepository.findAll());
        log.info("Movie cinemas: " + movieCinemaRepository.findAll());
        log.info("Movie genres: " + movieGenreRepository.findAll());
        log.info("Movies: " + movieRepository.findAll());
        log.info("Movie reviews: " + movieReviewRepository.findAll());
        log.info("NBP Logs: " + nbpLogRepository.findAll());
        log.info("NBP Roles: " + nbpRoleRepository.findAll());
        log.info("NBP Users: " + nbpUserRepository.findAll());
        log.info("Seats: " + seatRepository.findAll());
        log.info("Tickets: " + ticketRepository.findAll());
        log.info("Users: " + userRepository.findAll());
        log.info("Tokens: " + tokenRepository.findAll());
    }
}
