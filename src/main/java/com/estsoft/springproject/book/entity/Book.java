package com.estsoft.springproject.book.entity;

import com.estsoft.springproject.book.domain.response.BookResponse;
import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Getter
public class Book {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(nullable = false)
    private String name;
    @Column(nullable = false)
    private String author;


    //convert
    public BookResponse convertBookRes() {
        return BookResponse.builder()
                .id(this.id)
                .name(this.name)
                .author(this.author)
                .build();
    }
}
