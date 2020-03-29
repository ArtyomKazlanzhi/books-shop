package ua.atomspace.kazlanzhy.books.shop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.atomspace.kazlanzhy.books.shop.dao.model.Author;
import ua.atomspace.kazlanzhy.books.shop.dao.model.Book;
import ua.atomspace.kazlanzhy.books.shop.dao.model.Genre;
import ua.atomspace.kazlanzhy.books.shop.service.AuthorService;
import ua.atomspace.kazlanzhy.books.shop.service.BookService;
import ua.atomspace.kazlanzhy.books.shop.service.GenreService;

import javax.validation.Valid;
import java.util.LinkedList;
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

    @GetMapping("/add")
    public String addBook(Model model) {
        List<Genre> genres = genreService.list();
        List<Author> authors = authorService.list();
        model.addAttribute("genres", genres);
        model.addAttribute("authors", authors);
        model.addAttribute("book", new Book());
        return "add_book";
    }

    @PostMapping("/add")
    public String processBookAdding(@Valid Book book, Model model) {
        log.info("{}", book);
        bookService.create(book);
        return "redirect:/authors";
    }
}
