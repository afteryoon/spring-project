package com.estsoft.springproject.domain;

import jakarta.persistence.*;

@Entity
public class Members {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "member_id")
    private Long id;
    @Column()
    private String username;

    @ManyToOne
    @JoinColumn(name = "Team_id")   //FK
    private Team team;      //1:n 단방향 연관관계 매핑

    @OneToOne
    @JoinColumn(name = "locker_id")
    private Locker locker;
}
