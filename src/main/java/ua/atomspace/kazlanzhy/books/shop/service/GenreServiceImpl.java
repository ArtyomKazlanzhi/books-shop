package ua.atomspace.kazlanzhy.books.shop.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.atomspace.kazlanzhy.books.shop.dao.model.Genre;
import ua.atomspace.kazlanzhy.books.shop.dao.repository.GenreRepository;
import ua.atomspace.kazlanzhy.books.shop.exception.ResourceNotFoundException;

import java.util.List;

@Slf4j
@Service
public class GenreServiceImpl implements GenreService {

    private GenreRepository genreRepository;

    @Autowired
    public GenreServiceImpl(GenreRepository genreRepository) {
        this.genreRepository = genreRepository;
    }

    @Override
    public List<Genre> list() {
        return genreRepository.findAllByOrderByIdDesc();
    }

    @Override
    public Genre create(Genre genre) {
        return genreRepository.save(genre);
    }

    @Override
    public Genre get(Integer id) {
        return genreRepository.findById(id).orElseThrow(() -> logGenreNotFound(id));
    }

    @Override
    public Genre update(Genre request, Integer id) {
        Genre genre = genreRepository.findById(id).orElseThrow(() -> logGenreNotFound(id));

        genre.setName(request.getName());
        return genreRepository.save(genre);
    }

    @Override
    public Genre delete(Integer id) {
        Genre genre = genreRepository.findById(id).orElseThrow(() -> logGenreNotFound(id));
        genreRepository.delete(genre);
        return genre;
    }

    @Override
    public boolean isUnique(String name) {
        return !genreRepository.existsByName(name);
    }

    static ResourceNotFoundException logGenreNotFound(Integer id) {
        log.error("Genre with id = {} NOT_FOUND", id);
        return new ResourceNotFoundException("Genre with id = " + id + " not found");
    }
}
