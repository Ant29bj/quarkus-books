package org.acme.book;

import java.util.List;
import java.util.Optional;

public interface BookService {
    List<Book> FindMany();
    Optional<Book> FindOne(Long id);
    void CreateOne(Book book);
    boolean DeleteOne(Long id);
    Book UpdateOne(Long id, Book book) throws Exception;
}
