package ua.atomspace.kazlanzhy.books.shop.service;

import ua.atomspace.kazlanzhy.books.shop.dao.model.Genre;

import java.util.List;

public interface GenreService {

    List<Genre> list();

    Genre create(Genre genre);

    Genre get(Integer id);

    Genre update(Genre genre, Integer id);

    Genre delete(Integer id);

    boolean isUnique(String name);
}
