package org.acme.book;

import javax.persistence.*;
import java.time.LocalDate;

@Entity
@Table(name = "books")
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE)
    private Long id;

    private String title;
    private String autor;
    private LocalDate publication_date;
    private String isbn;


    public Book(Long id, String title, String autor, LocalDate publication_date, String isbn) {
        this.id = id;
        this.title = title;
        this.autor = autor;
        this.publication_date = publication_date;
        this.isbn = isbn;
    }

    public Book() {
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAutor() {
        return autor;
    }

    public void setAutor(String autor) {
        this.autor = autor;
    }

    public LocalDate getPublication_date() {
        return publication_date;
    }

    public void setPublication_date(LocalDate publication_date) {
        this.publication_date = publication_date;
    }

    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }
}
