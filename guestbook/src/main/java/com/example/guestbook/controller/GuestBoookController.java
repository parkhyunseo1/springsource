package com.example.guestbook.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.example.guestbook.dto.PageRequestDto;
import com.example.guestbook.service.GuestBookService;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@RequiredArgsConstructor
@Log4j2
@RequestMapping("/guestbook")
@Controller
public class GuestBoookController {
    private final GuestBookService service;

    @GetMapping("/list")
    public void getList(PageRequestDto requestDto, Model model) {
        log.info("list 요청");

        model.addAttribute("result", service.list(requestDto));
    }

}