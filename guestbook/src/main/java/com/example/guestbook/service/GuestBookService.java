package com.example.guestbook.service;

import org.springframework.stereotype.Service;

import com.example.guestbook.dto.GuestBookDto;
import com.example.guestbook.dto.PageRequestDto;
import com.example.guestbook.dto.PageResultDto;
import com.example.guestbook.entity.GuestBook;

@Service
public interface GuestBookService {
    // 등록
    Long register(GuestBookDto dto);

    // 조회
    GuestBookDto read(Long gno);

    // 전체 조회
    PageResultDto<GuestBookDto, GuestBook> list(PageRequestDto requestDto);

    // 수정
    Long update(GuestBookDto dto);

    // 삭제
    void delete(Long gno);

    // dtoToEntity
    default GuestBook dtoToEntity(GuestBookDto dto) {

        GuestBook entity = GuestBook.builder()
                .gno(dto.getGno())
                .writer(dto.getWriter())
                .title(dto.getTitle())
                .content(dto.getContent())
                .build();
        return entity;
    }

    // entityToDto
    default GuestBookDto entityToDto(GuestBook entity) {

        GuestBookDto dto = GuestBookDto.builder()
                .gno(entity.getGno())
                .writer(entity.getWriter())
                .title(entity.getTitle())
                .content(entity.getContent())
                .updateDate(entity.getUpdateDate())
                .regDate(entity.getRegDate())
                .build();
        return dto;
    }
}