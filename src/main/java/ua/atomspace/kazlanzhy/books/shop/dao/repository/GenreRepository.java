package ua.atomspace.kazlanzhy.books.shop.dao.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import ua.atomspace.kazlanzhy.books.shop.dao.model.Genre;

public interface GenreRepository extends JpaRepository<Genre, Integer> {
}
