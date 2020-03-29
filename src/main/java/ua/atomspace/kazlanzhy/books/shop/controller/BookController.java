package ua.atomspace.kazlanzhy.books.shop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.atomspace.kazlanzhy.books.shop.dao.model.Author;
import ua.atomspace.kazlanzhy.books.shop.dao.model.Book;
import ua.atomspace.kazlanzhy.books.shop.dao.model.Genre;
import ua.atomspace.kazlanzhy.books.shop.service.AuthorService;
import ua.atomspace.kazlanzhy.books.shop.service.BookService;
import ua.atomspace.kazlanzhy.books.shop.service.GenreService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequestMapping(path = "/books")
public class BookController {

    private BookService bookService;
    private GenreService genreService;
    private AuthorService authorService;

    @Autowired
    public BookController(BookService bookService, GenreService genreService, AuthorService authorService) {
        this.bookService = bookService;
        this.genreService = genreService;
        this.authorService = authorService;
    }

    @GetMapping
    public String getAllBooks(Model model) {
        List<Book> books = bookService.list();
        log.info("GET all books: {}", books);
        model.addAttribute("books", books);
        return "books";
    }

    @GetMapping("/add")
    public String addBook(Model model) {
        List<Genre> genres = genreService.list();
        List<Author> authors = authorService.list();
        model.addAttribute("genres", genres);
        model.addAttribute("genres", genres);
        model.addAttribute("authors", authors);
        model.addAttribute("book", new Book());
        return "add_book";
    }

    @PostMapping("/add")
    public String processBookAdding(@Valid Book book) {
        log.info("{}", book);
        bookService.create(book);
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable Integer id, Model model) {
        Book book = bookService.get(id);
        List<Genre> genres = genreService.list();
        List<Author> authors = authorService.list();
        model.addAttribute("genres", genres);
        model.addAttribute("selectedGenres", book.getGenres());
        model.addAttribute("selectedAuthors", book.getAuthors());
        model.addAttribute("authors", authors);
        model.addAttribute("book", book);
        return "edit_book";
    }

    @PostMapping("/edit")
    public String processBookEditing(@Valid Book book) {
        log.info("{}", book);
        bookService.update(book, book.getId());
        return "redirect:/books";
    }
}
