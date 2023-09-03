package com.books.springBook.Controllers;

import com.books.springBook.Models.Book;
import com.books.springBook.Repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.ArrayList;

@Controller
public class BookController {
    @Autowired
    private BookRepository repository;

    @GetMapping("/book")
    public String bookMain(Model model){
        Iterable<Book> books=repository.findAll();

        ArrayList<Book> arrBook=new ArrayList<Book>();
        for (Book book:books) {
            arrBook.add(book);
        }
        model.addAttribute("books", arrBook);
        return "book";
    }
}
