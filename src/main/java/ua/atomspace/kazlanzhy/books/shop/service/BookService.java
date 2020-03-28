package ua.atomspace.kazlanzhy.books.shop.service;

import ua.atomspace.kazlanzhy.books.shop.dao.model.Book;

import java.util.List;

public interface BookService {

    List<Book> list();

    Book create(Book book);

    Book get(Integer id);

    Book update(Book book, Integer id);

    Book delete(Integer id);

    List<Book> getBooksByGenre(Integer genreId);

    List<Book> getBooksByAuthor(Integer authorId);
}
