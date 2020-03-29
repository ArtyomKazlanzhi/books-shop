package ua.atomspace.kazlanzhy.books.shop.service.validation;

import org.springframework.beans.factory.annotation.Autowired;
import ua.atomspace.kazlanzhy.books.shop.service.GenreService;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class UniqueGenreValidator implements ConstraintValidator<UniqueName, String> {

    private GenreService genreService;

    @Autowired
    public UniqueGenreValidator(GenreService genreService) {
        this.genreService = genreService;
    }

    @Override
    public boolean isValid(String name, ConstraintValidatorContext constraintValidatorContext) {
        return genreService.isUnique(name);
    }
}
