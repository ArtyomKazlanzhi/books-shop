package ua.atomspace.kazlanzhy.books.shop.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import ua.atomspace.kazlanzhy.books.shop.dao.model.Author;
import ua.atomspace.kazlanzhy.books.shop.dao.repository.AuthorRepository;
import ua.atomspace.kazlanzhy.books.shop.exception.ResourceNotFoundException;

import java.util.List;

@Slf4j
@Service
public class AuthorServiceImpl implements AuthorService {

    private AuthorRepository authorRepository;

    @Autowired
    public AuthorServiceImpl(AuthorRepository authorRepository) {
        this.authorRepository = authorRepository;
    }

    @Override
    public List<Author> list() {
        return authorRepository.findAll();
    }

    @Override
    public Author create(Author author) {
        return authorRepository.save(author);
    }

    @Override
    public Author get(Integer id) {
        return authorRepository.findById(id)
                .orElseThrow(() -> logAuthorNotFound(id));
    }

    @Override
    public Author update(Author request, Integer id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> logAuthorNotFound(id));

        author.setFirstName(request.getFirstName());
        author.setLastName(request.getLastName());
        author.setPatronymic(request.getPatronymic());
        return authorRepository.save(author);
    }

    @Override
    public Author delete(Integer id) {
        Author author = authorRepository.findById(id).orElseThrow(() -> logAuthorNotFound(id));
        authorRepository.delete(author);
        return author;
    }

    static ResourceNotFoundException logAuthorNotFound(Integer id) {
        log.error("Author with id = {} NOT_FOUND", id);
        return new ResourceNotFoundException("Author with id = " + id + " not found");
    }
}
