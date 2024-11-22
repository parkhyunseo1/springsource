package com.example.board.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.board.dto.BoardDto;
import com.example.board.dto.PageRequestDto;
import com.example.board.dto.PageResultDto;
import com.example.board.entity.Board;
import com.example.board.service.BoardService;

import jakarta.validation.Valid;

import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@Log4j2
@RequiredArgsConstructor
@Controller
@RequestMapping("/board")
public class BoardController {
    private final BoardService service;

    @GetMapping("/list")
    public void getBoard(@ModelAttribute("requestDto") PageRequestDto requestDto, Model model) {
        log.info("게시판 요청");
        PageResultDto<BoardDto, Object[]> result = service.getList(requestDto);
        System.out.println(result);
        model.addAttribute("result", result);

    }

    @GetMapping({ "/read", "/modify" })
    public void getRead(@RequestParam Long bno, @ModelAttribute("requestDto") PageRequestDto requestDto, Model model) {
        log.info("상세조회{}", bno);

        BoardDto dto = service.read(bno);
        model.addAttribute("dto", dto);
    }

    @PreAuthorize("isAuthenticated()")
    @GetMapping("/create")
    public void getCreate(@ModelAttribute("dto") BoardDto dto,
            @ModelAttribute("requestDto") PageRequestDto requestDto) {
        log.info("등록 폼 요청");
    }

    @PreAuthorize("isAuthenticated()")
    @PostMapping("/create")
    public String postCreate(@Valid @ModelAttribute("dto") BoardDto dto, BindingResult result,
            @ModelAttribute("requestDto") PageRequestDto requestDto, RedirectAttributes rttr) {
        log.info("등록 요청 {}", dto);

        if (result.hasErrors()) {
            return "/board/create";
        }

        // service
        Long bno = service.register(dto);

        rttr.addAttribute("bno", bno);
        rttr.addAttribute("page", requestDto.getPage());
        rttr.addAttribute("size", requestDto.getSize());
        rttr.addAttribute("type", requestDto.getType());
        rttr.addAttribute("keyword", requestDto.getKeyword());

        return "redirect:/board/read";
    }

    @PreAuthorize("authentication.name == #dto.writerEmail")
    @PostMapping("/remove")
    public String postRemove(Long bno, String writerEmail, @ModelAttribute("requestDto") PageRequestDto requestDto,
            RedirectAttributes rttr) {
        log.info("삭제 요청 {}", bno);

        service.remove(bno);

        rttr.addAttribute("page", requestDto.getPage());
        rttr.addAttribute("size", requestDto.getSize());
        rttr.addAttribute("type", requestDto.getType());
        rttr.addAttribute("keyword", requestDto.getKeyword());
        return "redirect:/board/list";
    }

    // 로그인 사용자 == 작성자
    @PreAuthorize("authentication.name == #dto.writerEmail")
    @PostMapping("/modify")
    public String postModify(BoardDto dto, @ModelAttribute(name = "requestDto") PageRequestDto requestDto,
            RedirectAttributes rttr) {
        Long bno = service.update(dto);

        rttr.addFlashAttribute("msg", bno);
        rttr.addAttribute("bno", dto.getBno());
        rttr.addAttribute("page", requestDto.getPage());
        rttr.addAttribute("size", requestDto.getSize());
        rttr.addAttribute("keyword", requestDto.getKeyword());
        rttr.addAttribute("type", requestDto.getType());
        // 상세조회
        return "redirect:read";
    }

}
