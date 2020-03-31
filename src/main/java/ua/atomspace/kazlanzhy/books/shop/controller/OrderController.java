package ua.atomspace.kazlanzhy.books.shop.controller;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import ua.atomspace.kazlanzhy.books.shop.dao.model.Genre;
import ua.atomspace.kazlanzhy.books.shop.dao.model.Order;
import ua.atomspace.kazlanzhy.books.shop.service.OrderService;

import javax.validation.Valid;
import java.util.List;

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
    public String orderBook(@Valid Order order, BindingResult bindingResult, Model model) {
        if (bindingResult.hasErrors()) {
            model.addAttribute("book", order.getBook());
            return "order_book";
        }
        Order created = orderService.create(order);
        log.info("POST order on /order : {}", created);
        return "redirect:/orders/success";
    }

    @GetMapping
    public String getAllOrders(Model model) {
        List<Order> orders = orderService.list();
        model.addAttribute("orders", orders);
        log.info("GET orders list on /orders: {}", orders);
        return "admin/orders";
    }

    @GetMapping("/success")
    public String getSuccessPage() {
        log.info("GET on /orders/success");
        return "success_order";
    }
}
