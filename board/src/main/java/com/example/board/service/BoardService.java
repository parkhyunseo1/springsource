package com.example.board.service;

import org.springframework.stereotype.Service;

import com.example.board.dto.BoardDto;
import com.example.board.dto.PageRequestDto;
import com.example.board.dto.PageResultDto;
import com.example.board.entity.Board;
import com.example.board.entity.Member;

@Service
public interface BoardService {

    // crud
    Long register(BoardDto dto);

    PageResultDto<BoardDto, Object[]> getList(PageRequestDto requestDto);

    BoardDto read(Long bno);

    Long update(BoardDto dto);

    void remove(Long bno);

    // entity => dto
    default BoardDto entityToDto(Board board, Member member, Long replyCnt) {
        return BoardDto.builder()
                .bno(board.getBno())
                .title(board.getTitle())
                .content(board.getContent())
                .writerEmail(member.getEmail())
                .writerName(member.getName())
                .regDate(board.getRegDate())
                .updateDate(board.getUpdateDate())
                .replyCnt(replyCnt)
                .build();
    }

    // dto => entity
    default Board dtoToEntity(BoardDto dto) {

        Member member = Member.builder().email(dto.getWriterEmail()).build();

        return Board.builder()
                .bno(dto.getBno())
                .title(dto.getTitle())
                .content(dto.getContent())
                .writer(member)
                .build();
    }

}
