package ba.unsa.etf.nbp24t1.service;

import ba.unsa.etf.nbp24t1.entity.MovieEntity;
import ba.unsa.etf.nbp24t1.entity.MovieGenreEntity;
import ba.unsa.etf.nbp24t1.exception.NotFoundException;
import ba.unsa.etf.nbp24t1.repository.MovieGenreRepository;
import ba.unsa.etf.nbp24t1.repository.MovieRepository;
import com.itextpdf.kernel.colors.ColorConstants;
import com.itextpdf.kernel.pdf.PdfDocument;
import com.itextpdf.kernel.pdf.PdfWriter;
import com.itextpdf.layout.Document;
import com.itextpdf.layout.element.Cell;
import com.itextpdf.layout.element.Paragraph;
import com.itextpdf.layout.element.Table;
import com.itextpdf.layout.property.TextAlignment;
import com.itextpdf.layout.property.UnitValue;
import lombok.RequiredArgsConstructor;
import org.json.JSONException;
import org.json.JSONObject;
import org.springframework.http.*;
import org.springframework.stereotype.Service;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;

@RequiredArgsConstructor
@Service
public class MovieServiceImpl implements MovieService {

    private final MovieRepository movieRepository;

    private final MovieGenreRepository movieGenreRepository;

    @Override
    public List<MovieEntity> getAll() {
        return movieRepository.findAll();
    }

    @Override
    public ResponseEntity deleteMovie(int id) {
        if (movieRepository.existsById((long) id)) {
            JSONObject objekat = new JSONObject();
            Optional<MovieEntity> optionalMovie = movieRepository.findById((long) id);
            if (!optionalMovie.isPresent()) {
                throw new NotFoundException("Movie does not exist.");
            }
            MovieEntity film = optionalMovie.get();

            List<MovieGenreEntity> movieGenres = movieGenreRepository.findByMovieId(film.getId());
            movieGenreRepository.deleteAll(movieGenres);

            movieRepository.deleteById((long) id);
            try {
                objekat.put("message", "Movie deleted successfully!");
            } catch (JSONException e) {
                e.printStackTrace();
            }
            return new ResponseEntity(objekat.toString(), HttpStatus.OK);
        } else {
            throw new NotFoundException("Movie with Id " + id + " does not exist!");
        }
    }

    @Override
    public ResponseEntity addMovie(MovieEntity movie) {
        movie.setCreatedAt(LocalDateTime.now());
        movieRepository.save(movie);

        JSONObject objekat = new JSONObject();
        try {
            objekat.put("message", "Movie has been added successfully!");
        } catch (JSONException e) {
            e.printStackTrace();
        }
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(MediaType.APPLICATION_JSON);
        HttpEntity<MovieEntity> request = new HttpEntity<>(movie, headers);


        return new ResponseEntity(movie, HttpStatus.CREATED);
    }


    public List<MovieEntity> getMoviesFromLast7Days() {
        LocalDateTime now = LocalDateTime.now();
        LocalDateTime sevenDaysAgo = now.minusDays(7);
        return movieRepository.findByCreatedAtBetween(sevenDaysAgo, now);
    }

    public ByteArrayInputStream generateMoviesReportPdf() {
        List<MovieEntity> movies = getMoviesFromLast7Days();

        ByteArrayOutputStream out = new ByteArrayOutputStream();
        PdfWriter writer = new PdfWriter(out);
        PdfDocument pdf = new PdfDocument(writer);
        Document document = new Document(pdf);

        // Title
        Paragraph title = new Paragraph("Movies Released in Last 7 Days")
                .setFontSize(18)
                .setBold()
                .setTextAlignment(TextAlignment.CENTER)
                .setFontColor(ColorConstants.BLUE);
        document.add(title);
        document.add(new Paragraph(" "));

        // Table
        float[] columnWidths = {1, 2, 1, 3, 2};
        Table table = new Table(UnitValue.createPercentArray(columnWidths));
        table.setWidth(UnitValue.createPercentValue(100));

        // Table Header
        table.addHeaderCell(new Cell().add(new Paragraph("ID")).setBackgroundColor(ColorConstants.LIGHT_GRAY).setBold());
        table.addHeaderCell(new Cell().add(new Paragraph("Title")).setBackgroundColor(ColorConstants.LIGHT_GRAY).setBold());
        table.addHeaderCell(new Cell().add(new Paragraph("Duration")).setBackgroundColor(ColorConstants.LIGHT_GRAY).setBold());
        table.addHeaderCell(new Cell().add(new Paragraph("Description")).setBackgroundColor(ColorConstants.LIGHT_GRAY).setBold());
        table.addHeaderCell(new Cell().add(new Paragraph("Created At")).setBackgroundColor(ColorConstants.LIGHT_GRAY).setBold());

        // Date Formatter
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("dd/MM/yyyy HH:mm:ss");

        // Table Data
        for (MovieEntity movie : movies) {
            table.addCell(new Cell().add(new Paragraph(movie.getId().toString())));
            table.addCell(new Cell().add(new Paragraph(movie.getName())));
            table.addCell(new Cell().add(new Paragraph(movie.getDuration().toString())));
            table.addCell(new Cell().add(new Paragraph(movie.getDescription())));
            table.addCell(new Cell().add(new Paragraph(movie.getCreatedAt().format(formatter))));
        }

        document.add(table);
        document.close();

        return new ByteArrayInputStream(out.toByteArray());
    }
}
