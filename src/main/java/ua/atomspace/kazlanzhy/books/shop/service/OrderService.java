package ua.atomspace.kazlanzhy.books.shop.service;

import ua.atomspace.kazlanzhy.books.shop.dao.model.Order;

import java.util.List;

public interface OrderService {

    List<Order> list();

    Order create(Order order);

    Order get(Integer id);

    Order update(Order order, Integer id);

    Order delete(Integer id);
}
