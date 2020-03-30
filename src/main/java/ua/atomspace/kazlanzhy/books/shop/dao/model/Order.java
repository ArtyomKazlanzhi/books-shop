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
import javax.persistence.ManyToOne;
import javax.persistence.Table;
import javax.validation.constraints.Pattern;

@NoArgsConstructor
@AllArgsConstructor
@Getter
@Setter
@ToString
@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "name")
    @Length(min = 2, max = 50, message = "Name should be from 2 to 50 characters")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Should contain only characters")
    private String name;

    @Column(name = "last_name")
    @Length(min = 2, max = 50, message = "Last name should be from 2 to 50 characters")
    @Pattern(regexp = "^[a-zA-Z]*$", message = "Should contain only characters")
    private String lastName;

    @Column(name = "address")
    private String address;

    @Column(name = "quantity")
    private Integer quantity;

    @ManyToOne(cascade = {CascadeType.MERGE, CascadeType.DETACH, CascadeType.PERSIST})
    @JoinColumn(name = "book_id")
    private Book book;
}
