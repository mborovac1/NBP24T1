package ba.unsa.etf.nbp24t1.controller;

import ba.unsa.etf.nbp24t1.entity.MovieEntity;
import ba.unsa.etf.nbp24t1.service.MovieService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import lombok.RequiredArgsConstructor;
import org.springframework.core.io.InputStreamResource;
import org.springframework.http.HttpHeaders;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.io.ByteArrayInputStream;
import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/movies")
@RestController
@CrossOrigin
public class MovieController {

    private final MovieService movieService;

    @GetMapping("/")
    public List<MovieEntity> getAll() {
        return movieService.getAll();
    }

    @PostMapping("/add")
    @Operation(summary = "Adding new movie", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity addMovie(@RequestBody MovieEntity movie) {
        return movieService.addMovie(movie);
    }

    @DeleteMapping("/delete/{id}")
    @Operation(summary = "Deleting movie", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity deleteMovie(@PathVariable int id) {
        return movieService.deleteMovie(id);
    }

   /* @GetMapping("/report/last-7-days/pdf")
    @Operation(summary = "Downloading report pdf", security = @SecurityRequirement(name = "bearerAuth"))
    public ResponseEntity<InputStreamResource> getMoviesReportPdf() {
        ByteArrayInputStream bis = movieService.generateMoviesReportPdf();

        HttpHeaders headers = new HttpHeaders();
        headers.add("Content-Disposition", "inline; filename=movies_report.pdf");

        return ResponseEntity.ok()
                .headers(headers)
                .contentType(MediaType.APPLICATION_PDF)
                .body(new InputStreamResource(bis));
    }*/
}
