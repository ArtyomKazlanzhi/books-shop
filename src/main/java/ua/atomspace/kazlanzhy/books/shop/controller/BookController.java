package ua.atomspace.kazlanzhy.books.shop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.atomspace.kazlanzhy.books.shop.dao.model.Author;
import ua.atomspace.kazlanzhy.books.shop.dao.model.Book;
import ua.atomspace.kazlanzhy.books.shop.dao.model.Genre;
import ua.atomspace.kazlanzhy.books.shop.dao.model.Order;
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
        log.info("GET book list on /books/{}", books);
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
        log.info("GET new Book on /books/add");
        return "add_book";
    }

    @PostMapping("/add")
    public String processBookAdding(@Valid Book book) {
        Book created = bookService.create(book);
        log.info("POST book on /authors/add {}", created);
        return "redirect:/books";
    }

    @GetMapping("/edit/{id}")
    public String editBook(@PathVariable Integer id, Model model) {
        Book book = bookService.get(id);
        model.addAttribute("genres", genreService.list());
        model.addAttribute("selectedGenres", book.getGenres());
        model.addAttribute("selectedAuthors", book.getAuthors());
        model.addAttribute("authors", authorService.list());
        model.addAttribute("book", book);
        log.info("GET book on /authors/edit{}: {}", id, book);
        return "edit_book";
    }

    @PostMapping("/edit")
    public String processBookEditing(@Valid Book book, BindingResult bindingResult) {
        Book updated = bookService.update(book, book.getId());
        log.info("UPDATED book on /books/edit {}", updated);
        return "redirect:/books";
    }

    @GetMapping("/author/{authorId}")
    public String getAuthorBooks(@PathVariable Integer authorId, Model model) {
        List<Book> booksByAuthor = bookService.getBooksByAuthor(authorId);
        model.addAttribute("books", booksByAuthor);
        log.info("GET books by author list on books/author/{}: {}", authorId, booksByAuthor);
        return "book_list";
    }

    @GetMapping("/genre/{genreId}")
    public String getGenreBooks(@PathVariable Integer genreId, Model model) {
        List<Book> booksByGenre = bookService.getBooksByGenre(genreId);
        model.addAttribute("books", booksByGenre);
        log.info("GET books by genre on books/genre/{}: {}", genreId, booksByGenre);
        return "book_list";
    }

    @GetMapping("/{bookId}")
    public String getBook(@PathVariable Integer bookId, Model model) {
        Book book = bookService.get(bookId);
        Order order = new Order();
        order.setBook(book);
        model.addAttribute("order", order);
        model.addAttribute("book", book);
        log.info("GET book on books/{}: {}", bookId, book);
        return "order_book";
    }
}
