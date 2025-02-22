package com.library.management.service.impl;

import com.library.management.exception.ResourceNotFoundException;
import com.library.management.repository.BookRepository;
import jakarta.validation.Valid;
import org.modelmapper.ModelMapper;
import com.library.management.dto.BookDTO;
import com.library.management.entity.Book;
import com.library.management.service.BookService;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class BookServiceImpl implements BookService {
    private final ModelMapper modelMapper;
    private final BookRepository bookRepository;

    public BookServiceImpl(ModelMapper modelMapper, BookRepository bookRepository) {
        this.modelMapper = modelMapper;
        this.bookRepository = bookRepository;
    }


    @Override
    public BookDTO createBook(@Valid BookDTO bookDTO) {
        return modelMapper.map(bookRepository.save(modelMapper.map(bookDTO,Book.class)),BookDTO.class);
    }


    @Override
    public BookDTO updateBook(@Valid BookDTO bookDTO) {
        Book book = bookRepository.findById(bookDTO.getId()).orElseThrow(()-> new ResourceNotFoundException("Book is not present","bootId",bookDTO.getId()));
       Book updatedBook = book.toBuilder()
               .author(bookDTO.getAuthor())
               .title(bookDTO.getTitle())
               .publicationYear(bookDTO.getPublicationYear())
               .build();
        // book.setTitle(bookDTO.getTitle());
        // book.setAuthor(bookDTO.getAuthor());
        // book.setPublicationYear(bookDTO.getPublicationYear());
        return modelMapper.map(bookRepository.save(updatedBook),BookDTO.class);
    }

    @Override
    public void deleteBook(long id) {
        Book book = bookRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Book is not present","bootId",id));
        bookRepository.delete(book);
    }

    @Override
    public BookDTO getBook(long id) {
        Book book = bookRepository.findById(id).orElseThrow(()-> new ResourceNotFoundException("Book is not present","bootId",id));
        return modelMapper.map(book,BookDTO.class);
    }

    @Override
    public List<BookDTO> getAllBooks() {
        return bookRepository.findAll().stream().map(book->modelMapper.map(book,BookDTO.class)).collect(Collectors.toList());
    }
}
