package org.example.ebookdeliveryservice.controller;

import jakarta.mail.MessagingException;
import org.example.ebookdeliveryservice.service.MailService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/ebook-delivery-service")
public class EmailController {

    private static final Logger logger = LoggerFactory.getLogger(EmailController.class);
    private final MailService mailService;

    public EmailController(MailService mailService) {
        this.mailService = mailService;
    }

    @GetMapping("/send-email")
    public ResponseEntity<String> sendEmail(@RequestParam String mail) {
        try {
            mailService.sendEmail(mail);
            return ResponseEntity.ok("Email sent successfully to " + mail);
        } catch (MessagingException e) {
            logger.error("Failed to send email to {}: {}", mail, e.getMessage());
            return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR)
                    .body("Failed to send email due to server error.");
        }
    }
}
