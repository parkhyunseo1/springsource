package com.example.memo.service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;

import com.example.memo.dto.MemoDto;
import com.example.memo.entity.Memo;
import com.example.memo.repository.MemoRespository;

import lombok.RequiredArgsConstructor;

@RequiredArgsConstructor
@Service
public class MemoServiceImpl implements MemoService {

    private final MemoRespository memoRespository;

    @Override
    public Long create(MemoDto dto) {
        // Memo memo = Memo.builder().memoText(dto.getMemoText()).build();
        Memo memo = dtoToEntity(dto);
        return memoRespository.save(memo).getMno();
    }

    @Override
    public MemoDto read(Long id) {
        Memo memo = memoRespository.findById(id).get();
        return entityToDto(memo);
    }

    @Override
    public List<MemoDto> list() {
        List<Memo> list = memoRespository.findAll();

        return list.stream().map(memo -> entityToDto(memo)).collect(Collectors.toList());
    }

    @Override
    public Long update(MemoDto dto) {
        // Memo memo = memoRespository.findById(7L).get();
        // memo.setMemoText("안녕하세요");
        Memo memo = dtoToEntity(dto);
        return memoRespository.save(memo).getMno();

    }

    @Override
    public void delete(Long id) {
        memoRespository.deleteById(id);
    }

}
