package com.estsoft.springproject.book.controller;

import com.estsoft.springproject.book.domain.request.CreateBookReq;
import com.estsoft.springproject.book.domain.response.BookResponse;
import com.estsoft.springproject.book.entity.Book;
import com.estsoft.springproject.book.service.BookService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/books")
public class BookController {
    final BookService bookService;

    @GetMapping()
    public ResponseEntity<List<BookResponse>> getAllBooks() {
        List<BookResponse> bookList = bookService.findAllBook().stream()
                .map(Book::convertBookRes)
                .toList();

        return ResponseEntity.ok(bookList);
    }

    // book create
    @PostMapping()
    public ResponseEntity<BookResponse> addBook(@RequestParam String author, @RequestParam String name) {
        BookResponse book = bookService.createBook(new CreateBookReq(author, name)).convertBookRes();
        return ResponseEntity.ok(book);
    }
}
