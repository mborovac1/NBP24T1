package ba.unsa.etf.nbp24t1.service;

import ba.unsa.etf.nbp24t1.entity.GenreEntity;
import ba.unsa.etf.nbp24t1.repository.GenreRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@RequiredArgsConstructor
@Service
public class GenreServiceImpl implements GenreService {

    private final GenreRepository genreRepository;

    @Override
    public List<GenreEntity> getAll() {
        return genreRepository.findAll();
    }
}
