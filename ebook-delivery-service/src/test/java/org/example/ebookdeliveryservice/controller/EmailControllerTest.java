package org.example.ebookdeliveryservice.controller;

import jakarta.mail.MessagingException;

import org.example.ebookdeliveryservice.service.MailService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;



import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

class EmailControllerTest {

    @Mock
    private MailService mailService;

    private EmailController emailController;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.openMocks(this);
        emailController = new EmailController(mailService);
    }

    @Test
    void testSendEmailSuccess() throws MessagingException {
        String email = "test@example.com";

        doNothing().when(mailService).sendEmail(email);

        ResponseEntity<String> response = emailController.sendEmail(email);

        assertEquals(HttpStatus.OK, response.getStatusCode());
        assertEquals("Email sent successfully to " + email, response.getBody());

        verify(mailService, times(1)).sendEmail(email);
    }

    @Test
    void testSendEmailFailure() throws MessagingException {
        String email = "test@example.com";

        doThrow(new MessagingException("SMTP error")).when(mailService).sendEmail(email);

        ResponseEntity<String> response = emailController.sendEmail(email);

        assertEquals(HttpStatus.INTERNAL_SERVER_ERROR, response.getStatusCode());
        assertEquals("Failed to send email due to server error.", response.getBody());

        verify(mailService, times(1)).sendEmail(email);
    }
}
