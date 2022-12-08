package com.frailty.backend.email;

import com.frailty.backend.localisation.Localiser;
import lombok.AllArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import javax.mail.MessagingException;
import javax.mail.internet.MimeMessage;

@Service
@AllArgsConstructor
@Slf4j //equivalent to private final static Logger LOGGER = LoggerFactory.getLogger(EmailService.class);
public class EmailService implements ISendEmail {
    private static final String EMAIL_SENDER = "ARISE_App@ntu.edu.sg";

    private final JavaMailSender javaMailSender;

    private final Localiser localiser;

    @Override
    @Async
    public void send(String to, String email) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper = new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(email, true);
            helper.setTo(to);
            helper.setSubject("Confirm your email");
            helper.setFrom(EMAIL_SENDER);
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            log.error("Failed to send email", e);
            throw new IllegalStateException(localiser.fail("Failed to send email."));
        }
    }
}
