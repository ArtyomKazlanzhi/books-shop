package ua.atomspace.kazlanzhy.books.shop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.web.servlet.error.ErrorController;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.servlet.RequestDispatcher;
import javax.servlet.http.HttpServletRequest;

@Slf4j
@Controller
public class AppErrorController implements ErrorController {

    private static final String PATH = "/error";

    @RequestMapping("/error")
    public String handleError(HttpServletRequest request, Model model) {
        Object status = request.getAttribute(RequestDispatcher.ERROR_STATUS_CODE);
        model.addAttribute("status", status);
        log.error("{} on {} from user {}", status, request.getAttribute(RequestDispatcher.ERROR_REQUEST_URI),
                request.getUserPrincipal());
        return "/error/error";
    }

    @Override
    public String getErrorPath() {
        return PATH;
    }
}
