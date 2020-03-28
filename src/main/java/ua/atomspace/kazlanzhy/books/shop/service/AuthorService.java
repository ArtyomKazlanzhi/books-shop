package ua.atomspace.kazlanzhy.books.shop.service;

import ua.atomspace.kazlanzhy.books.shop.dao.model.Author;

import java.util.List;

public interface AuthorService {

    List<Author> list();

    Author create(Author author);

    Author get(Integer id);

    Author update(Author author, Integer id);

    Author delete(Integer id);
}
