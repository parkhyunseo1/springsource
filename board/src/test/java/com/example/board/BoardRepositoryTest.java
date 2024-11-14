package com.example.board;

import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.shadow.com.univocity.parsers.annotations.Replace;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.board.entity.Board;
import com.example.board.entity.Member;
import com.example.board.entity.Reply;
import com.example.board.repository.BoardRepository;
import com.example.board.repository.MemberRepository;
import com.example.board.repository.ReplyRepository;

@SpringBootTest
public class BoardRepositoryTest {

    @Autowired
    private BoardRepository boardRepository;

    @Autowired
    private MemberRepository memberRepository;

    @Autowired
    private ReplyRepository replyRepository;

    @Test
    public void testInsertMember() {
        IntStream.rangeClosed(1, 30).forEach(i -> {

            Member member = Member.builder()
                    .email("eamil" + i + "@gmail.com")
                    .name("name" + i)
                    .password("password" + i)
                    .build();

            memberRepository.save(member);
        });
    }

    @Test
    public void testInsertBoard() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            int num = (int) (Math.random() * 30) + 1;
            Member member = Member.builder().email("eamil" + num + "@gmail.com").build();
            Board board = Board.builder()
                    .title("title" + i)
                    .content("content" + i)
                    .writer(member)
                    .build();

            boardRepository.save(board);
        });
    }

    @Test
    public void testInsertReply() {
        IntStream.rangeClosed(1, 100).forEach(i -> {
            long bno = (long) (Math.random() * 100) + 206;
            Board board = Board.builder().bno(bno).build();

            Reply reply = Reply.builder()
                    .text("text" + i)
                    .replyer("replyer" + i)
                    .board(board)
                    .build();

            replyRepository.save(reply);
        });
    }
}
