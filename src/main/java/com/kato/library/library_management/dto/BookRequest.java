package com.kato.library.library_management.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class BookRequest {
    private String title;
    private String author;
    private String isbn;
    private Integer publishedYear;
    private String description;
    private String coverUrl;
    private String status;
    private Long categoryId; // quan trọng nè
}
