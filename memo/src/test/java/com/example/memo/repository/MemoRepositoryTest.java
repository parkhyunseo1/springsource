package com.example.memo.repository;

import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.LongStream;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import com.example.memo.entity.Memo;

@SpringBootTest
public class MemoRepositoryTest {

    @Autowired
    private MemoRepository memoRepository;

    @Test
    public void testMemoInsert() {

        LongStream.rangeClosed(1, 100).forEach(i -> {
            Memo memo = Memo.builder().memoText("memo text" + i).build();
            memoRepository.save(memo);
        });
    }

    @Test
    public void testMemoRead() {
        // 6번 메모 가져오기
        Memo memo = memoRepository.findById(6L).get();
        System.out.println(memo);

        System.out.println();

        // 전체 메모 가져오기
        List<Memo> list = memoRepository.findAll();
        list.forEach(m -> System.out.println(m));
    }

    @Test
    public void testMemoUpdate() {
        // 27번 메모 내용 수정
        Memo memo = memoRepository.findById(27L).get();
        memo.setMemoText("memo 수정");
        memoRepository.save(memo);
    }

    @Test
    public void testMemoDelete() {
        // 메모 삭제
        memoRepository.deleteById(30L);
    }

    @Test
    public void testMnoList() {
        // memoRepository.findByMnoLessThan(30L).forEach(m -> System.out.println(m));

        // memoRepository.findByMnoLessThanOrderByMnoDesc(30L).forEach(m ->
        // System.out.println(m));
        memoRepository.findByMnoBetween(30L, 60L).forEach(m -> System.out.println(m));
    }
}
