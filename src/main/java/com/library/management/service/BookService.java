package com.library.management.service;


import com.library.management.dto.BookDTO;

import java.util.List;

public interface BookService {
    BookDTO createBook(BookDTO bookDTO);
    BookDTO updateBook(BookDTO bookDTO);
    void deleteBook(long id);
    BookDTO getBook(long id);
    List<BookDTO> getAllBooks();

}
