package com.example.board.service;

import java.util.List;

import com.example.board.dto.BoardDto;
import com.example.board.dto.ReplyDto;
import com.example.board.entity.Board;
import com.example.board.entity.Reply;

public interface ReplyService {

    Long register(ReplyDto dto);

    List<ReplyDto> list(Long bno);

    ReplyDto read(Long rno);

    Long modify(ReplyDto replyDto);

    void remove(Long rno);

    // entity => dto
    default ReplyDto entityToDto(Reply reply) {
        return ReplyDto.builder()
                .rno(reply.getRno())
                .text(reply.getText())
                .replyer(reply.getReplyer())
                .bno(reply.getBoard().getBno())
                .regDate(reply.getRegDate())
                .updateDate(reply.getUpdateDate())
                .build();
    }

    // dto => entity
    default Reply dtoToEntity(ReplyDto dto) {
        Board board = Board.builder().bno(dto.getBno()).build();

        Reply entity = Reply.builder()
                .rno(dto.getRno())
                .text(dto.getText())
                .replyer(dto.getReplyer())
                .board(board)
                .build();
        return entity;
    }
    // dto => entity
}
