package com.estsoft.springproject.book.domain.request;

import com.estsoft.springproject.book.entity.Book;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@AllArgsConstructor
@NoArgsConstructor
@Getter @Setter
public class CreateBookReq {
    private String name;
    private String author;

    public Book toBook() {
        return Book.builder()
                .name(this.name)
                .author(this.author)
                .build();
    }
}
