package com.jsm.mm.eventpublisher.event;

import com.jsm.mm.domain.certify.Certify;
import com.jsm.mm.domain.member.Member;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class SignUpEvent {

    private Member member;
    private Certify certify;
}
