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
        AppUser appUser = getValidUser(email);
        if (appUser.isEnabled()) {
            throw new IllegalStateException(localiser.duplicate("validated account", email));
        }

        String token = confirmationTokenService.generateConfirmationToken(appUser);
        List<String> res = new ArrayList<>();
        res.add(appUser.getFirstName());
        res.add(token);
        return res;
    }

    public AppUser getValidUser(String email) {
        AppUser appUser = appUserRepository.findByEmail(email).orElseThrow(() -> new IllegalStateException(localiser.notFound("Email", email)));
        return appUser;
    }

    public int enableUser(String email) {
        return appUserRepository.updateEnabledUser(email);
    }

}
