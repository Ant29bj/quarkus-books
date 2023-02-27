package org.acme.book;

import javax.enterprise.context.ApplicationScoped;
import javax.inject.Inject;
import javax.persistence.PersistenceContext;
import javax.transaction.Transactional;
import java.util.List;
import java.util.Optional;

@ApplicationScoped
public class BookServiceImpl implements BookService{
    @PersistenceContext
    @Inject
    BookRepository bookRepository;

    @Override
    public List<Book> FindMany() {
        return bookRepository.listAll();
    }

    @Override
    @Transactional
    public void CreateOne(Book book){
        bookRepository.persist(book);
    }

    @Override
    @Transactional
    public Optional<Book> FindOne(Long id){
        return bookRepository.findByIdOptional(id);
    }

    @Override
    @Transactional
    public boolean DeleteOne(Long id){
        return bookRepository.deleteById(id);
    }

    @Override
    @Transactional
    public Book UpdateOne(Long id, Book book) throws Exception{
        Optional<Book> oBook = bookRepository.findByIdOptional(id);
        if (oBook.isEmpty()){
            throw new Exception();
        }else{
            oBook.get().setAutor(book.getAutor());
            oBook.get().setIsbn(book.getIsbn());
            oBook.get().setTitle(book.getTitle());
            oBook.get().setPublication_date(book.getPublication_date());

            bookRepository.persist(oBook.get());
            return oBook.get();
        }
    }

}
