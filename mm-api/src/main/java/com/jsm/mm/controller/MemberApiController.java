package com.jsm.mm.controller;

import com.jsm.mm.dto.request.member.SignUpRequestDto;
import com.jsm.mm.service.MemberService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/member")
public class MemberApiController {

    private final MemberService memberService;

    @PostMapping("/signUp")
    public void signUp(@RequestBody SignUpRequestDto signUpRequestDto) {
        memberService.signUp(signUpRequestDto);
    }

    @GetMapping("/exist/{column}")
    public boolean isExistMember(@PathVariable String column, @RequestParam String v) {
        return memberService.isExistMember(column, v);
    }
}
