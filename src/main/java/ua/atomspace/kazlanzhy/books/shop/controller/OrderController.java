package ua.atomspace.kazlanzhy.books.shop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.atomspace.kazlanzhy.books.shop.dao.model.Book;
import ua.atomspace.kazlanzhy.books.shop.dao.model.Order;
import ua.atomspace.kazlanzhy.books.shop.service.OrderService;

@Slf4j
@Controller
@RequestMapping(path = "/orders")
public class OrderController {

    private OrderService orderService;

    @Autowired
    public OrderController(OrderService orderService) {
        this.orderService = orderService;
    }

    @PostMapping
    public String orderBook(Order order){
        log.info("{}", order);
        orderService.create(order);
        return "redirect:/authors/select";
    }
}
