package com.estsoft.springproject.book.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter @Setter
@NoArgsConstructor
@AllArgsConstructor
public class BookDTO {
    private Long id;
    private String name;
    private String author;
}
