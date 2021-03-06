package ua.atomspace.kazlanzhy.books.shop.dao.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;
import org.hibernate.validator.constraints.Length;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;
import java.util.List;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "authors")
public class Author {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "last_name")
    @Length(min = 2, max = 50, message = "Last name should be from 2 to 50 characters")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Should contain only characters")
    private String lastName;

    @Column(name = "first_name")
    @Length(min = 2, max = 50, message = "First name should be from 2 to 50 characters")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Should contain only characters")
    private String firstName;

    @Column(name = "patronymic")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Should contain only characters")
    private String patronymic;

    @ToString.Exclude
    @ManyToMany(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST})
    @JoinTable(
            name = "books_to_authors",
            joinColumns = @JoinColumn(name = "author_id"),
            inverseJoinColumns = @JoinColumn(name = "book_id"))
    private List<Book> books;
}
