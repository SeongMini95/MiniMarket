package com.jsm.mm.eventpublisher.handler;

import com.jsm.mm.eventpublisher.event.SignUpEvent;
import com.jsm.mm.util.MailHandler;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Profile;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Component;
import org.springframework.transaction.event.TransactionalEventListener;
import org.thymeleaf.spring5.SpringTemplateEngine;

import java.util.HashMap;
import java.util.Map;

@RequiredArgsConstructor
@Component
@Profile("!not-signUpEmail")
public class SignUpEventHandler {

    private final JavaMailSender mailSender;
    private final SpringTemplateEngine templateEngine;

    @Async
    @TransactionalEventListener
    public void sendSignUpEmail(SignUpEvent event) throws Exception {
        Map<String, Object> context = new HashMap<>();
        context.put("certifyKey", event.getCertify().getCertifyKey());

        MailHandler mailHandler = new MailHandler(mailSender);
        mailHandler.setFrom("modify879@gmail.com", "MiniMarket");
        mailHandler.setTo(event.getMember().getEmail());
        mailHandler.setSubject("회원가입 인증 for MiniMarket");
        mailHandler.setContentTemplate("mail/signUp", context, templateEngine);

        mailHandler.send();
    }
}
