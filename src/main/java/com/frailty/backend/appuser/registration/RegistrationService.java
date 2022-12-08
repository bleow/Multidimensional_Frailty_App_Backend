package com.frailty.backend.appuser.registration;

import com.frailty.backend.email.ConfirmationTokenEmail;
import com.frailty.backend.email.ISendEmail;
import com.frailty.backend.localisation.Localiser;
import com.frailty.backend.appuser.registration.token.ConfirmationToken;
import com.frailty.backend.appuser.registration.token.ConfirmationTokenService;
import com.frailty.backend.appuser.AppUserRole;
import com.frailty.backend.appuser.AppUserService;
import lombok.AllArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Service
@AllArgsConstructor
public class RegistrationService {
    private static final String URL = "http://localhost:8080/api/v1/";
    private final AppUserService appUserService;
    private ConfirmationTokenService confirmationTokenService;
    private EmailValidator emailValidator;
    private Localiser localiser;
    @Autowired
    private final ISendEmail emailSender;

    public String register(RegistrationRequest request) {
        boolean isValidEmail = emailValidator.test(request.getEmail());
        if (!isValidEmail) {
            throw new IllegalStateException(localiser.invalid("Email", request.getEmail()));
        }
        String token = appUserService.signup(request.getFirstName(),request.getLastName(), request.getEmail(), request.getPassword(), AppUserRole.PATIENT);
        String link = URL + "registration/confirm?token=" + token;
        emailSender.send(request.getEmail(), ConfirmationTokenEmail.build(request.getFirstName(), link));
        return token;
    }

    public String resendToken(String email) {
        boolean isValidEmail = emailValidator.test(email);
        if (!isValidEmail) {
            throw new IllegalStateException(localiser.invalid("Email", email));
        }
        List<String> res = appUserService.resendConfirmationToken(email);
        String link = URL + "registration/confirm?token=" + res.get(1);
        emailSender.send(email, ConfirmationTokenEmail.build(res.get(0), link));
        return res.get(1);
    }

    @Transactional
    public String confirmToken(String token) {
        ConfirmationToken confirmationToken = confirmationTokenService.getConfirmationToken(token).orElseThrow(
                () -> new IllegalStateException(localiser.notFound("Token", token)));

        if (confirmationToken.getConfirmedAt() != null) {
            throw new IllegalStateException(localiser.fail("Email already activated."));
        }

        if (confirmationToken.getExpiresAt().isBefore((LocalDateTime.now()))) {
            throw new IllegalStateException(localiser.fail("Token expired"));
        }

        int res = confirmationTokenService.setConfirmedAt(token);
        if (res != 1) {
            throw new IllegalStateException(localiser.fail("Confirmation token was not set as confirmed."));
        }
        int res2 = appUserService.enableUser(confirmationToken.getAppUser().getEmail());
        if (res2 != 1) {
            throw new IllegalStateException(localiser.fail("User was not set as enabled."));
        }
        return localiser.success("User registered");
    }
}
