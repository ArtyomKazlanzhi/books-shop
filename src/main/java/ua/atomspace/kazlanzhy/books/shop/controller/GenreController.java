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
import ua.atomspace.kazlanzhy.books.shop.dao.model.Genre;
import ua.atomspace.kazlanzhy.books.shop.service.GenreService;

import java.util.List;

@Slf4j
@Controller
@RequestMapping(path = "/genres")
public class GenreController {

    private GenreService genreService;

    @Autowired
    public GenreController(GenreService genreService) {
        this.genreService = genreService;
    }

    @GetMapping("/select")
    public String getGenresForSelect(Model model) {
        List<Genre> list = genreService.list();
        model.addAttribute("genres", list);
        log.info("GET genres list on /genres/select: {}", list);
        return "select_genre";
    }

    @GetMapping("/add")
    public String addGenre(Model model) {
        model.addAttribute("genre", new Genre());
        log.info("GET new Genre on /genres/add");
        return "add_genre";
    }

    @PostMapping("/add")
    public String processGenreAdding(Genre genre, BindingResult bindingResult) {
        if (!bindingResult.hasErrors()) {
            log.info("POST genre on genres/add: {}", genre);
            genreService.create(genre);
            return "redirect:/genres";
        }
        return "add_genre";
    }

    @GetMapping
    public String getAllGenres(Model model) {
        List<Genre> genres = genreService.list();
        model.addAttribute("genres", genres);
        log.info("GET genre list on /genres/select: {}", genres);
        return "genres";
    }

    @GetMapping("/edit/{id}")
    public String editGenre(@PathVariable Integer id, Model model) {
        Genre genre = genreService.get(id);
        model.addAttribute("genre", genre);
        log.info("GET genre on genres/edit{}: {}", id, genre);
        return "edit_genre";
    }

    @PostMapping("/edit")
    public String processBookEditing(Genre genre) {
        Genre updated = genreService.update(genre, genre.getId());
        log.info("UPDATED genre on genres/edit: {}", updated);
        return "redirect:/genres";
    }
}
