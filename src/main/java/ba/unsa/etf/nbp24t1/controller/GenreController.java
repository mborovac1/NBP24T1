package ba.unsa.etf.nbp24t1.controller;

import ba.unsa.etf.nbp24t1.entity.GenreEntity;
import ba.unsa.etf.nbp24t1.service.GenreService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RequiredArgsConstructor
@RequestMapping("/api/genres")
@RestController
@CrossOrigin
public class GenreController {

    private final GenreService genreService;

    @GetMapping("/")
    public List<GenreEntity> getAll() {
        return genreService.getAll();
    }
}
