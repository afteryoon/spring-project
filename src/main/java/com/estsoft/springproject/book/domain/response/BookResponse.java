package com.estsoft.springproject.book.domain.response;

import com.estsoft.springproject.book.entity.Book;
import lombok.*;

@Getter @Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class BookResponse {
    private Long id;
    private String name;
    private String author;

    public BookResponse(Book book) {
        this.id = book.getId();
        this.name = book.getName();
        this.author = book.getAuthor();
    }
}
