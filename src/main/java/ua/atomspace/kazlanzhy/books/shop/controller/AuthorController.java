package ua.atomspace.kazlanzhy.books.shop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import ua.atomspace.kazlanzhy.books.shop.dao.model.Author;
import ua.atomspace.kazlanzhy.books.shop.dao.model.Genre;
import ua.atomspace.kazlanzhy.books.shop.service.AuthorService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
public class AuthorController {

    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/authors")
    public String getGenres(Model model) {
        List<Author> list = authorService.list();
        log.info("GET authors list: {}", list);
        model.addAttribute("authors", list);
        return "authors";
    }

    @GetMapping("/addAuthor")
    public String addGenres(Model model) {
        model.addAttribute("author", new Author());
        return "add_author";
    }

    @PostMapping("/addAuthor")
    public String addGenre(@Valid Author author, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()){
            log.info("POST author: {}", author);
            authorService.create(author);
            return "redirect:/authors";
        }
        return "add_author";
    }
}
