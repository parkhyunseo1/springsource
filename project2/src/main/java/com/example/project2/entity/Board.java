package com.example.project2.entity;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.SequenceGenerator;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

// @GeneratedValue(strategy = GenerationType.AUTO) : JPA 구현체가 자동으로 생성 전략 결정
// @GeneratedValue(strategy = GenerationType.IDENTITY) : 기본키 생성을 데이터베이스에 위임하는 전략(MySQL, PostgreSQL)

@NoArgsConstructor
@AllArgsConstructor
@Data
@Builder
@SequenceGenerator(name = "board_seq_gen", sequenceName = "board_seq", allocationSize = 1)
@Entity(name = "proBoard")
public class Board {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "board_seq_gen")
    private Long id;

    @Column(nullable = false)
    private String title;

    @Column(nullable = false)
    private String content;

    @Column(nullable = false)
    private String writer;

}
