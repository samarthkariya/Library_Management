package com.library.management.serviceTest;

import com.library.management.dto.BookDTO;
import com.library.management.entity.Book;
import com.library.management.exception.ResourceNotFoundException;
import com.library.management.repository.BookRepository;
import com.library.management.service.impl.BookServiceImpl;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.modelmapper.ModelMapper;

import java.util.Arrays;
import java.util.List;
import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class BookServiceImplTest {

    @Mock
    private BookRepository bookRepository;
    @Mock
    private ModelMapper modelMapper;
    @InjectMocks
    private BookServiceImpl bookService;
    private Book book;
    private BookDTO bookDTO;

    @BeforeEach
    void setUp() {
        book = Book.builder()
                .id(1L)
                .title("Test Book")
                .author("Test Author")
                .publicationYear("2025")
                .build();

        bookDTO = BookDTO.builder()
                .id(1L)
                .title("Test Book")
                .author("Test Author")
                .publicationYear("2025")
                .build();
    }

    @Test
    void testCreateBook() {
        when(modelMapper.map(any(BookDTO.class), eq(Book.class))).thenReturn(book);
        when(bookRepository.save(any(Book.class))).thenReturn(book);
        when(modelMapper.map(any(Book.class), eq(BookDTO.class))).thenReturn(bookDTO);

        BookDTO createdBook = bookService.createBook(bookDTO);

        assertNotNull(createdBook);
        assertEquals(bookDTO.getTitle(), createdBook.getTitle());
        assertEquals(bookDTO.getAuthor(), createdBook.getAuthor());
        assertEquals(bookDTO.getPublicationYear(), createdBook.getPublicationYear());

        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    void testGetBookById_Success() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(modelMapper.map(any(Book.class), eq(BookDTO.class))).thenReturn(bookDTO);

        BookDTO foundBook = bookService.getBook(1L);

        assertNotNull(foundBook);
        assertEquals(bookDTO.getTitle(), foundBook.getTitle());
        assertEquals(bookDTO.getAuthor(), foundBook.getAuthor());

        verify(bookRepository, times(1)).findById(1L);
    }

    @Test
    void testGetBookById_NotFound() {
        when(bookRepository.findById(2L)).thenReturn(Optional.empty());

        ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class,
                () -> bookService.getBook(2L));

        assertEquals("Book is not present having bootId : 2", thrown.getMessage());
        verify(bookRepository, times(1)).findById(2L);
    }

    @Test
    void testUpdateBook() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        when(bookRepository.save(any(Book.class))).thenReturn(book);
        when(modelMapper.map(any(Book.class), eq(BookDTO.class))).thenReturn(bookDTO);

        BookDTO updatedBook = bookService.updateBook(bookDTO);

        assertNotNull(updatedBook);
        assertEquals(bookDTO.getTitle(), updatedBook.getTitle());
        assertEquals(bookDTO.getAuthor(), updatedBook.getAuthor());

        verify(bookRepository, times(1)).findById(1L);
        verify(bookRepository, times(1)).save(any(Book.class));
    }

    @Test
    void testDeleteBook_Success() {
        when(bookRepository.findById(1L)).thenReturn(Optional.of(book));
        doNothing().when(bookRepository).delete(any(Book.class));

        assertDoesNotThrow(() -> bookService.deleteBook(1L));

        verify(bookRepository, times(1)).findById(1L);
        verify(bookRepository, times(1)).delete(book);
    }

    @Test
    void testDeleteBook_NotFound() {
        when(bookRepository.findById(2L)).thenReturn(Optional.empty());

        ResourceNotFoundException thrown = assertThrows(ResourceNotFoundException.class,
                () -> bookService.deleteBook(2L));

        assertEquals("Book is not present having bootId : 2", thrown.getMessage());
        verify(bookRepository, times(1)).findById(2L);
        verify(bookRepository, never()).delete(any(Book.class));
    }

    @Test
    void testGetAllBooks() {
        List<Book> books = Arrays.asList(book);

        when(bookRepository.findAll()).thenReturn(books);
        when(modelMapper.map(any(Book.class), eq(BookDTO.class))).thenReturn(bookDTO);

        List<BookDTO> retrievedBooks = bookService.getAllBooks();

        assertNotNull(retrievedBooks);
        assertEquals(1, retrievedBooks.size());
        assertEquals("Test Book", retrievedBooks.get(0).getTitle());

        verify(bookRepository, times(1)).findAll();
    }
}

