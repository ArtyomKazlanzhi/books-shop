package ua.atomspace.kazlanzhy.books.shop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import ua.atomspace.kazlanzhy.books.shop.dao.model.Genre;
import ua.atomspace.kazlanzhy.books.shop.service.GenreService;

import javax.validation.Valid;
import java.util.List;

@Slf4j
@Controller
public class GenreController {

    private GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/genres")
    public String getGenres(Model model) {
        List<Genre> list = genreService.list();
        log.info("GET genres list: {}", list);
        model.addAttribute("genres", list);
        return "genres";
    }

    @GetMapping("/addGenre")
    public String addGenres(Model model) {
        model.addAttribute("genre", new Genre());
        return "add_genre";
    }

    @PostMapping("/addGenre")
    public String addGenre(@Valid @ModelAttribute Genre genre) {
        log.info("POST genre: {}", genre);
        genreService.create(genre);
        return "redirect:/genres";
    }
}
