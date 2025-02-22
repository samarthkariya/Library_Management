package com.library.management.controller;

import com.library.management.dto.BookDTO;
import com.library.management.service.BookService;

import io.swagger.v3.oas.annotations.Operation;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/v1/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @GetMapping
    @Operation(summary = "Get all books", description = "Retrieves a list of all books.")
    public ResponseEntity<List<BookDTO>> getAllBooks(){
        return new ResponseEntity<>(bookService.getAllBooks(), HttpStatus.OK);
    }

    @GetMapping("/{id}")
    @Operation(summary = "Get book by ID", description = "Retrieves a specific book by its ID.")
    public ResponseEntity<BookDTO> getBookById(@PathVariable long id){
        return new ResponseEntity<>(bookService.getBook(id), HttpStatus.OK);
    }

    @PostMapping
    @Operation(summary = "Create a new book", description = "Adds a new book to the library.")
    public ResponseEntity<BookDTO> createBook(@Valid @RequestBody BookDTO bookDTO){
        return new ResponseEntity<>(bookService.createBook(bookDTO),HttpStatus.CREATED);
    }

    @PutMapping
    @Operation(summary = "Update book", description = "Updates an existing book.")
    public ResponseEntity<BookDTO> updateBook(@Valid @RequestBody BookDTO bookDTO){
        return new ResponseEntity<>(bookService.updateBook(bookDTO),HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    @Operation(summary = "Delete book", description = "Removes a book from the library.")
    public ResponseEntity<Void> deleteBook(@PathVariable long id){
        bookService.deleteBook(id);
        return ResponseEntity.noContent().build();
    }
}
