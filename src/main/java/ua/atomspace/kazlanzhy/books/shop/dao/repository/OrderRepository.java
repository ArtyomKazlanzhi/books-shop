package ua.atomspace.kazlanzhy.books.shop.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.atomspace.kazlanzhy.books.shop.dao.model.Order;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
