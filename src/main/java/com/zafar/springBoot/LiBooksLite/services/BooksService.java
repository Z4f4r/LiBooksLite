package com.zafar.springBoot.LiBooksLite.services;

import com.zafar.springBoot.LiBooksLite.models.Book;
import com.zafar.springBoot.LiBooksLite.models.Person;
import com.zafar.springBoot.LiBooksLite.repositories.BooksRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional(readOnly = true)
public class BooksService {
    private final BooksRepository booksRepository;

    @Autowired
    public BooksService(BooksRepository booksRepository) {
        this.booksRepository = booksRepository;
    }

    public List<Book> showAll() {
        return booksRepository.findAll();
    }

    public Book show(int id) {
        Optional<Book> foundBook = booksRepository.findById(id);
        return foundBook.orElse(null);
    }

    @Transactional
    public void save(Book book) {
        booksRepository.save(book);
    }

    @Transactional
    public void update(int id, Book bookToBeUpdated) {
        bookToBeUpdated.setId(id);
        booksRepository.save(bookToBeUpdated);
    }

    @Transactional
    public void delete(int id) {
        booksRepository.deleteById(id);
    }

    // Returns null if book has no owner
    public Person getBookOwner(int id) {
        // Hibernate.initialize() is not needed here, since the owner (side One) is not lazily loaded
        return booksRepository.findById(id).map(Book::getOwner).orElse(null);
    }

    @Transactional
    public void release(int id) {
        booksRepository.findById(id).ifPresent(book -> book.setOwner(null));
    }

    @Transactional
    public void assign(int id, Person selectedPerson) {
        booksRepository.findById(id).ifPresent(book -> book.setOwner(selectedPerson));
    }
}
