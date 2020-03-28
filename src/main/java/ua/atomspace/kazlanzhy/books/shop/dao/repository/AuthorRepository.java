package ua.atomspace.kazlanzhy.books.shop.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.atomspace.kazlanzhy.books.shop.dao.model.Author;

public interface AuthorRepository extends JpaRepository<Author, Integer> {
}
