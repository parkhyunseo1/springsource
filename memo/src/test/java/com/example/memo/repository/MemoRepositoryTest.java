package com.example.memo.repository;

import java.util.List;
import java.util.stream.IntStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.memo.entity.Memo;

@SpringBootTest
public class MemoRepositoryTest {

    @Autowired
    private MemoRespository memoRespository;

    @Test
    public void testMemoInsert() {

        IntStream.rangeClosed(1, 10).forEach(i -> {
            Memo memo = Memo.builder().memoText("memo text" + i).build();
            memoRespository.save(memo);
        });

    }

    @Test
    public void testMemoRead() {
        // 6번 메모 가져오기
        Memo memo = memoRespository.findById(6L).get();
        System.out.println(memo);

        System.out.println();
        // 전체 메모 가져오기
        List<Memo> list = memoRespository.findAll();
        list.forEach(m -> System.out.println(m));
    }

    @Test
    public void testMemoUpdate() {
        // 7번 메모 내용 수정

        Memo memo = memoRespository.findById(37L).get();
        memo.setMemoText("안녕하세요");
        memoRespository.save(memo);
    }

    @Test
    public void testMemoDelete() {
        // 마지막 메모 삭제

        memoRespository.deleteById(30L);

    }
}
