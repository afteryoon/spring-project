package com.estsoft.springproject.book.service;

import com.estsoft.springproject.book.entity.Book;
import com.estsoft.springproject.book.repository.BookRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.*;

@SpringBootTest
@AutoConfigureMockMvc
class BookServiceTest {

    @InjectMocks
    private BookService bookService;

    @Mock
    private BookRepository bookRepository;

    @Test
    @DisplayName("책 단건 조회")
    public void findByBook() throws Exception{
        //given
        Book savedBook = bookRepository.save(createBook());
        //when

        //then
    }

    private Book createBook(){
        return Book.builder()
                .name("testBook")
                .author("testAuthor")
                .build();
    }

}