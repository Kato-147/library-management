package com.kato.library.library_management.service;

import com.kato.library.library_management.dto.BookRequest;
import com.kato.library.library_management.entity.Book;
import com.kato.library.library_management.entity.Category;
import com.kato.library.library_management.repository.BookRepository;
import com.kato.library.library_management.repository.CategoryRepository;
import org.springframework.stereotype.Service;

@Service
public class BookService {

    private final BookRepository bookRepository;
    private final CategoryRepository categoryRepository;

    public BookService(BookRepository bookRepository, CategoryRepository categoryRepository) {
        this.bookRepository = bookRepository;
        this.categoryRepository = categoryRepository;
    }

    public Book createBook(BookRequest request) {

        Category category = categoryRepository.findById(request.getCategoryId())
                .orElseThrow(() -> new RuntimeException("Category không tồn tại"));

        Book book = new Book();
        book.setTitle(request.getTitle());
        book.setAuthor(request.getAuthor());
        book.setIsbn(request.getIsbn());
        book.setPublishedYear(request.getPublishedYear());
        book.setDescription(request.getDescription());
        book.setCoverUrl(request.getCoverUrl());
        book.setStatus(request.getStatus());
        book.setCategory(category);

        return bookRepository.save(book);
    }
}
