package com.estsoft.springproject.book.controller;

import com.estsoft.springproject.book.domain.request.CreateBookReq;
import com.estsoft.springproject.book.domain.response.BookResponse;
import com.estsoft.springproject.book.entity.Book;
import com.estsoft.springproject.book.service.BookService;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("/books")
public class BookPageController {
    private final BookService bookService;

    public BookPageController(BookService bookService) {
        this.bookService = bookService;
    }

    //GET /books
    @GetMapping
    public String showBooks(Model model) {
        List<BookResponse> bookList = bookService.findAllBook().stream()
                .map(Book::convertBookRes)
                .toList();
        model.addAttribute("bookList", bookList);

        return "book/bookManagement";
    }

    //GET /books/{id}
    @GetMapping("/{id}")
    public String showBookDetail(
            Model model
            ,@PathVariable Long id
            ) {
        BookResponse book = new BookResponse(bookService.findBy(id));
        model.addAttribute("book", book);

        return "book/bookDetail";
    }

    //Post /book/
    @PostMapping()
    public String addBook(@RequestParam String author, @RequestParam String name) {
        bookService.createBook(new CreateBookReq(author, name));
        return "redirect:/books";
    }
}
