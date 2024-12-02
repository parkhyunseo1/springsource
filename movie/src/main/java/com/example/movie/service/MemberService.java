package com.example.movie.service;

import org.springframework.stereotype.Service;

import com.example.movie.dto.MemberDto;
import com.example.movie.dto.PasswordDto;

@Service
public interface MemberService {

    // 닉네임 수정
    void nickNameUpdate(MemberDto memberDto);

    // 비밀번호 수정
    void passwordUpdate(PasswordDto passwordDto) throws Exception;
    // 회원가입
    // 회원탈퇴
}
