package com.book.demo.entity;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table(
        name = "author",
        uniqueConstraints = @UniqueConstraint(columnNames = {"firstName", "lastName"}))
public class AuthorEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(
            name = "first_name",
            length = 30,
            unique = true,
            nullable = false
    )
    private String firstName;

    @Column(
            name = "last_name",
            length = 30,
            unique = true,
            nullable = false
    )
    private String lastName;


    @ManyToMany(
            mappedBy = "authors",
            fetch = FetchType.LAZY
    )
    private List<BookEntity> books;

    public AuthorEntity(String firstName, String lastName, List<BookEntity> books) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.books = books;
    }

    public AuthorEntity() {
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public List<BookEntity> getBooks() {
        return books;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public void setBooks(List<BookEntity> books) {
        this.books = books;
    }
}
