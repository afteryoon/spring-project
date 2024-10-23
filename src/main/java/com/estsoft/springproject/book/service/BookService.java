package com.estsoft.springproject.book.service;

import com.estsoft.springproject.book.domain.request.CreateBookReq;
import com.estsoft.springproject.book.entity.Book;
import com.estsoft.springproject.book.repository.BookRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class BookService {
    private final BookRepository bookRepository;

    public BookService(BookRepository bookRepository) {
        this.bookRepository = bookRepository;
    }

    //생성
    public Book addBook(CreateBookReq book) {
        return bookRepository.save(book.toBook());
    }

    //전체 조회
    public List<Book> findAllBook() {
        return bookRepository.findAll(Sort.by("id").descending()); //default 는 asc
        //query
        //select * from Book orderBy id desc
    }

    //단건 조회
    public Book findBy(Long id) {
        return bookRepository.findById(id).orElseThrow(
                ()-> new IllegalArgumentException("not found book id"));
    }

    //생성
    public Book createBook(CreateBookReq book) {
        return bookRepository.save(book.toBook());
    }

    //수정
    public Book updateBook(Book book) {
        return bookRepository.save(book);
    }


}
