package com.example.guestbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import com.example.guestbook.dto.GuestBookDto;
import com.example.guestbook.dto.PageRequestDto;
import com.example.guestbook.dto.PageResultDto;
import com.example.guestbook.entity.GuestBook;
import com.example.guestbook.service.GuestBookService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

@RequiredArgsConstructor
@Log4j2
@RequestMapping("/guestbook")
@Controller
public class GuestBoookController {
    private final GuestBookService service;

    @GetMapping("/list")
    public void getList(@ModelAttribute("requestDto") PageRequestDto requestDto, Model model) {
        log.info("list 요청");
        PageResultDto<GuestBookDto, GuestBook> result = service.list(requestDto);
        System.out.println(result);
        model.addAttribute("result", service.list(requestDto));
    }

    @GetMapping({ "/read", "/modify" })
    public void getRow(@RequestParam Long gno, @ModelAttribute(name = "requestDto") PageRequestDto requestDto,
            Model model) {
        log.info("상세조회 {}", gno);
        GuestBookDto dto = service.read(gno);

        model.addAttribute("dto", dto);

    }

    @PostMapping("/modify")
    public String postModify(GuestBookDto dto, @ModelAttribute(name = "requestDto") PageRequestDto requestDto,
            RedirectAttributes rttr) {

        log.info("수정 요청 {}" + dto);
        Long gno = service.update(dto);
        // 수정 완료 후 상세조회로 이동
        rttr.addAttribute("gno", gno);
        rttr.addAttribute("page", requestDto.getPage());
        rttr.addAttribute("size", requestDto.getSize());
        rttr.addAttribute("keyword", requestDto.getKeyword());
        rttr.addAttribute("type", requestDto.getType());
        return "redirect:read";
    }

    @PostMapping("/remove")
    public String postRemove(Long gno, @ModelAttribute(name = "requestDto") PageRequestDto requestDto,
            RedirectAttributes rttr) {
        log.info("삭제 요청 {}" + gno);
        log.info("requestDto {}" + requestDto);
        service.delete(gno);
        // 전체 목록으로 이동

        rttr.addAttribute("page", requestDto.getPage());
        rttr.addAttribute("size", requestDto.getSize());
        rttr.addAttribute("keyword", requestDto.getKeyword());
        rttr.addAttribute("type", requestDto.getType());

        return "redirect:list";
    }

    @GetMapping("/register")
    public void getRegister(@ModelAttribute("dto") GuestBookDto dto) {
        log.info("guestbook 작성 폼 요청");
    }

    @PostMapping("/register")
    public String postRegister(@Valid @ModelAttribute("dto") GuestBookDto dto, BindingResult result,
            RedirectAttributes rttr) {
        log.info("guestbook 등록 요청 {}", dto);
        if (result.hasErrors()) {
            return "/guestbook/register";
        }

        Long gno = service.register(dto);

        rttr.addFlashAttribute("msg", gno);
        rttr.addAttribute("page", 1);
        rttr.addAttribute("size", 20);
        rttr.addAttribute("keyword", "");
        rttr.addAttribute("type", "");

        return "redirect:list";
    }

}
