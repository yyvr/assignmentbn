package com.basen.demoapp;

import java.util.List;
import java.util.Optional;

import com.basen.demoapp.model.Book;
import com.basen.demoapp.repo.BookRepository;
import org.springframework.web.bind.annotation.*;

@RestController
class ControllerRest {

    private final BookRepository bookRepository;

    ControllerRest(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
        this.bookRepository.addDemoData();
    }

    @GetMapping("/books")
    List<Book> allBooks() {
        return bookRepository.findAll();
    }

    @GetMapping("/books/{id}")
    Optional<Book> bookById(@PathVariable String id) {
        return bookRepository.findById(id);
    }

    @PostMapping("/books")
    Book newBook(@RequestBody Book book) {
        return bookRepository.save(book);
    }

    @PutMapping("/books/{id}")
    Book replaceBook(@RequestBody Book newBook, @PathVariable String id) {
        return bookRepository.update(newBook, id);
    }

    @DeleteMapping("/books/{id}")
    void deleteBook(@PathVariable String id) {
        bookRepository.deleteById(id);
    }
}

// curl -X GET 127.0.0.1:8080/books |json_pp
// curl -X GET 127.0.0.1:8080/books/1 |json_pp
// curl -X POST 127.0.0.1:8080/books -H "content-type: application/json" --data @book.json
// curl -X PUT -H "Content-Type: application/json" -d @bookmodify.json 127.0.0.1:8080/books/1