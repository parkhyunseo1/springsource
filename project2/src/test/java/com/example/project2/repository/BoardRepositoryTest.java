package com.example.project2.repository;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.project2.entity.Board;

@SpringBootTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Test
    public void insertTest() {
        IntStream.rangeClosed(0, 19)
                .forEach(i -> {
                    Board board = Board.builder()
                            .title("title" + i)
                            .content("content" + i)
                            .writer("writer" + i)
                            .build();
                    boardRepository.save(board);
                });
    }

    @Test
    public void selectOneTest() {
        System.out.println(boardRepository.findById(2L));
    }

    @Test
    public void selectAllTest() {
        boardRepository.findAll().forEach(board -> System.out.println(board));
    }

    @Test
    public void updateTest() {
        Board board = boardRepository.findById(5L).get();
        board.setTitle("박현서");
        board.setWriter("박현서");
        board.setContent("안녕하세요");
        boardRepository.save(board);
    }

    @Test
    public void deleteTest() {
        boardRepository.deleteById(20L);
    }
}
