package ua.atomspace.kazlanzhy.books.shop.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.atomspace.kazlanzhy.books.shop.dao.model.Author;
import ua.atomspace.kazlanzhy.books.shop.dao.model.Book;
import ua.atomspace.kazlanzhy.books.shop.dao.model.Genre;
import ua.atomspace.kazlanzhy.books.shop.dao.repository.AuthorRepository;
import ua.atomspace.kazlanzhy.books.shop.dao.repository.BookRepository;
import ua.atomspace.kazlanzhy.books.shop.dao.repository.GenreRepository;
import ua.atomspace.kazlanzhy.books.shop.exception.ResourceNotFoundException;

import java.util.List;

import static ua.atomspace.kazlanzhy.books.shop.service.AuthorServiceImpl.logAuthorNotFound;
import static ua.atomspace.kazlanzhy.books.shop.service.GenreServiceImpl.logGenreNotFound;

@Slf4j
@Service
public class BookServiceImpl implements BookService {

    private BookRepository bookRepository;
    private GenreRepository genreRepository;
    private AuthorRepository authorRepository;

    @Autowired
    public BookServiceImpl(BookRepository bookRepository, GenreRepository genreRepository, AuthorRepository authorRepository) {
        this.bookRepository = bookRepository;
        this.genreRepository = genreRepository;
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Book> list() {
        return bookRepository.findAllByOrderByIdDesc();
    }

    @Override
    public Book create(Book book) {
        return bookRepository.save(book);
    }

    @Override
    public Book get(Integer id) {
        return bookRepository.findById(id).orElseThrow(() -> logBookNotFound(id));
    }

    @Override
    public Book update(Book request, Integer id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> logBookNotFound(id));

        book.setTitle(request.getTitle());
        book.setDescription(request.getDescription());
        book.setPrice(request.getPrice());
        book.setGenres(request.getGenres());
        book.setAuthors(request.getAuthors());
        return bookRepository.save(book);
    }

    @Override
    public Book delete(Integer id) {
        Book book = bookRepository.findById(id).orElseThrow(() -> logBookNotFound(id));
        bookRepository.delete(book);
        return book;
    }

    @Override
    public List<Book> getBooksByGenre(Integer genreId) {
        Genre genre = genreRepository.findById(genreId).orElseThrow(() -> logGenreNotFound(genreId));
        return bookRepository.findAllByGenres(genre);
    }

    @Override
    public List<Book> getBooksByAuthor(Integer authorId) {
        Author author = authorRepository.findById(authorId).orElseThrow(() -> logAuthorNotFound(authorId));
        return bookRepository.findAllByAuthors(author);
    }

    static ResourceNotFoundException logBookNotFound(Integer id) {
        log.error("Book with id = {} NOT_FOUND", id);
        return new ResourceNotFoundException("Book with id = " + id + " not found");
    }
}
