package com.books.springBook.Controllers;

import com.books.springBook.Models.Book;
import com.books.springBook.Repo.BookRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class MainController {
    @Autowired
    private BookRepository repository;

    @GetMapping("/")
    public String main(Model model){
       Iterable<Book> books=repository.findAll();
       model.addAttribute("books", books);

        return "main";
    }
    @GetMapping("/add")
    public String bookAdd(Model model){
        return "/addBook";
    }

    @PostMapping("/add")
    public String createBook(@RequestParam String title, @RequestParam String author, @RequestParam int year ,
                             @RequestParam String genre, @RequestParam int pages, @RequestParam String description,
                             Model model){
        Book book=new Book(title, author,year,genre,pages,description);
        repository.save(book);
        return "redirect:/";
    }
    @GetMapping("/{id}")
    public String infoById(@PathVariable(value = "id") Long id, Model model){
        if(!repository.existsById(id)){
            return "redirect:/";
        }
        else{
            Book book=repository.findById(id).get();
            model.addAttribute("book", book);
            return "bookInfo";
        }
    }
    @GetMapping("/{id}/delete")
    public String bookDelete(@PathVariable(value = "id") Long id, Model model){
        Book book=repository.findById(id).get();
        repository.delete(book);
        return "redirect:/";
    }

    @GetMapping("/{id}/edit")
    public String bookEdit(@PathVariable(value = "id")Long id, Model model){
        if(!repository.existsById(id)){
            return "redirect:/";
        }
        else{
            Book book=repository.findById(id).get();
            model.addAttribute("book", book);
            return "editBook";
        }
    }

    @PostMapping("/{id}/edit")
    public String bookUpdate(@PathVariable(value = "id")Long id,@RequestParam String title,
                             @RequestParam String author, @RequestParam int year,@RequestParam String genre,
                             @RequestParam int pages, @RequestParam String description , Model model){

        Book book=repository.findById(id).get();
        book.setTitle(title);
        book.setAuthor(author);
        book.setYear(year);
        book.setGenre(genre);
        book.setPages(pages);
        book.setDescription(description);
        repository.save(book);
        return "redirect:/";
    }
}
