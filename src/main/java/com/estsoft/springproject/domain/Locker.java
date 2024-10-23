package com.estsoft.springproject.domain;

import jakarta.persistence.*;

@Entity
public class Locker {
    @Id
    @GeneratedValue
    @Column(name = "locker_id")
    private Long id;

    private String name;

    @OneToOne(mappedBy = "locker")
    private Members members;        //1:1 양방향 연관관계 매핑

}
