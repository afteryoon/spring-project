package com.estsoft.springproject.domain;

import jakarta.persistence.*;

import java.util.ArrayList;
import java.util.List;

@Entity
public class Team {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "team_id")
    private Long id;

    @Column()
    private String team_name;

    // 양방향 연관관계 -> @OneToMany(X)
    // 연관관계의 주인 명시
    @OneToMany(mappedBy = "team")
    List<Members> members = new ArrayList<>();
}
