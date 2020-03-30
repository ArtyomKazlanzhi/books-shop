package ua.atomspace.kazlanzhy.books.shop.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;
import ua.atomspace.kazlanzhy.books.shop.dao.model.Order;
import ua.atomspace.kazlanzhy.books.shop.dao.repository.OrderRepository;
import ua.atomspace.kazlanzhy.books.shop.exception.ResourceNotFoundException;

import java.util.List;

@Slf4j
@Service
public class OrderServiceImpl implements OrderService {

    private OrderRepository orderRepository;

    public OrderServiceImpl(OrderRepository orderRepository) {
        this.orderRepository = orderRepository;
    }

    @Override
    public List<Order> list() {
        return orderRepository.findAll();
    }

    @Override
    public Order create(Order order) {
        return orderRepository.save(order);
    }

    @Override
    public Order get(Integer id) {
        return orderRepository.findById(id).orElseThrow(() -> logOrderNotFound(id));
    }

    @Override
    public Order update(Order request, Integer id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> logOrderNotFound(id));

        order.setLastName(request.getLastName());
        order.setName(request.getName());
        order.setAddress(request.getAddress());
        order.setQuantity(request.getQuantity());
        order.setBook(request.getBook());
        return orderRepository.save(order);
    }

    @Override
    public Order delete(Integer id) {
        Order order = orderRepository.findById(id).orElseThrow(() -> logOrderNotFound(id));
        orderRepository.delete(order);
        return order;
    }

    static ResourceNotFoundException logOrderNotFound(Integer id) {
        log.error("Order with id = {} NOT_FOUND", id);
        return new ResourceNotFoundException("Order with id = " + id + " not found");
    }
}
