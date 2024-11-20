package com.example.board.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;

import com.example.board.entity.Board;
import com.example.board.entity.Reply;

public interface ReplyRepository extends JpaRepository<Reply, Long> {

    // reply 테이블의 board_bno 를 이용해 reply 삭제 하는 쿼리 메소드를 생성해야함
    @Modifying
    @Query("DELETE FROM Reply r WHERE r.board.bno = :bno")
    void deleteByBno(Long bno);

    // 특정 Bno의 댓글추출
    List<Reply> findByBoardOrderByRno(Board board);
}
