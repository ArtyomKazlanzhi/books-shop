package ua.atomspace.kazlanzhy.books.shop.controller;


import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping(value = {"/", "/home"})
    public String getHomePage() {
        return "redirect:/genres/select";
    }

    @GetMapping("/login")
    public String getLoginPage() {
        return "login";
    }
}
