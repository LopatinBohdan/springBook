package com.books.springBook.Repo;

import com.books.springBook.Models.Book;
import org.springframework.data.repository.CrudRepository;

public interface BookRepository extends CrudRepository<Book, Long> { }
