package com.frailty.backend.service;

import com.frailty.backend.entity.AppUser;
import com.frailty.backend.entity.AppUserRole;
import com.frailty.backend.output.Localiser;
import com.frailty.backend.repository.AppUserRepository;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@AllArgsConstructor
public class AppUserService implements UserDetailsService {

    private Localiser localiser;
    private final AppUserRepository appUserRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return appUserRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(localiser.notFound("Email", email)));
    }

    @Transactional
    public String signup(String firstName, String lastName, String email, String password, AppUserRole appUserRole) {
        boolean isUserExisting = appUserRepository.findByEmail(email).isPresent();
        if (isUserExisting) {
            throw new IllegalStateException(localiser.duplicate("Email", email));
        }

        String encodedPassword = bCryptPasswordEncoder.encode(password);
        AppUser appUser = new AppUser(firstName, lastName, email, encodedPassword, appUserRole);

        appUserRepository.save(appUser);

        String token = confirmationTokenService.generateConfirmationToken(appUser);
        return token;
    }

    public List<String> resendConfirmationToken(String email) {
        AppUser appUser = appUserRepository.findByEmail(email).orElse(null);
        if (Objects.isNull(appUser)) {
            throw new IllegalStateException(localiser.notFound("Email", email));
        }

        String token = confirmationTokenService.generateConfirmationToken(appUser);
        List<String> res = new ArrayList<>();
        res.add(appUser.getFirstName());
        res.add(token);
        return res;
    }

    public int enableUser(String email) {
        return appUserRepository.updateEnabledUser(email);
    }

}
