package ua.atomspace.kazlanzhy.books.shop.service.validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = UniqueGenreValidator.class)
@Retention(RetentionPolicy.RUNTIME)
@Target({ ElementType.FIELD })
public @interface UniqueName {

    public String message() default "There is already genre with this name!";

    public Class<?>[] groups() default {};

    public Class<? extends Payload>[] payload() default{};

}
