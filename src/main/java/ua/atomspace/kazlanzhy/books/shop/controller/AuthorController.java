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
import ua.atomspace.kazlanzhy.books.shop.service.AuthorService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
@RequestMapping(path = "/authors")
public class AuthorController {

    private AuthorService authorService;

    @Autowired
    public AuthorController(AuthorService authorService) {
        this.authorService = authorService;
    }

    @GetMapping("/select")
    public String getAuthorsForSelect(Model model) {
        List<Author> list = authorService.list();
        log.info("GET authors list: {}", list);
        model.addAttribute("authors", list);
        return "select_author";
    }

    @GetMapping("/add")
    public String addAuthor(Model model) {
        model.addAttribute("author", new Author());
        return "add_author";
    }

    @PostMapping("/add")
    public String processAuthorAdding(@Valid Author author, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            log.info("POST author: {}", author);
            authorService.create(author);
            return "redirect:/authors/select";
        }
        return "add_author";
    }

    @GetMapping
    public String getAllAuthors(Model model) {
        List<Author> authors = authorService.list();
        log.info("GET all authors: {}", authors);
        model.addAttribute("authors", authors);
        return "authors";
    }

    @GetMapping("/edit/{id}")
    public String editAuthor(@PathVariable Integer id, Model model) {
        Author author = authorService.get(id);
        model.addAttribute("author", author);
        return "edit_author";
    }

    @PostMapping("/edit")
    public String processAuthorEditing(Author author) {
        log.info("{}", author);
        authorService.update(author, author.getId());
        return "redirect:/authors";
    }
}
