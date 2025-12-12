package com.kato.library.library_management.controller;

import com.kato.library.library_management.dto.BookRequest;
import com.kato.library.library_management.entity.Book;
import com.kato.library.library_management.service.BookService;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/books")
public class BookController {

    private final BookService bookService;

    public BookController(BookService bookService) {
        this.bookService = bookService;
    }

    @PostMapping
    @PreAuthorize("hasRole('ADMIN')")
    public Book createBook(@RequestBody BookRequest req) {
        return bookService.createBook(req);
    }
}
