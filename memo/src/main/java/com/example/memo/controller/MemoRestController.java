package com.example.memo.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.memo.dto.MemoDto;
import com.example.memo.service.MemoService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@RequestMapping("/rest")
@Log4j2
@RestController
public class MemoRestController {
    private final MemoService memoService;

    @GetMapping("/list")
    public List<MemoDto> getList() {
        log.info("메모 전체 목록 요청");
        List<MemoDto> list = memoService.list();
        return list;
    }

    @PutMapping("/{mno}")
    public ResponseEntity<String> putMethodName(@PathVariable String id, @RequestBody String entity) {
        // TODO: process PUT request

        return entity;
    }

    @DeleteMapping("/{mno}")
    public ResponseEntity<String> getRemove(@PathVariable Long mno) {
        log.info("메모 삭제 요청 {}", mno);

        memoService.delete(mno);

        return new ResponseEntity<String>("SUCCESS", HttpStatus.OK);
    }
}
