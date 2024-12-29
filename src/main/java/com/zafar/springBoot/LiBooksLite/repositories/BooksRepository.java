package com.zafar.springBoot.LiBooksLite.repositories;

import com.zafar.springBoot.LiBooksLite.models.Book;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface BooksRepository extends JpaRepository<Book, Integer> {
}
