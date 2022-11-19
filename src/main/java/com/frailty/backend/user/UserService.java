package com.frailty.backend.user;

import com.frailty.backend.localisation.Localiser;
import com.frailty.backend.registration.token.ConfirmationToken;
import com.frailty.backend.registration.token.ConfirmationTokenService;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.*;

@Service
@AllArgsConstructor
public class UserService implements UserDetailsService {

    private Localiser localiser;
    private final UserRepository userRepository;
    private final BCryptPasswordEncoder bCryptPasswordEncoder;
    private final ConfirmationTokenService confirmationTokenService;

    @Override
    public UserDetails loadUserByUsername(String email) throws UsernameNotFoundException {
        return userRepository.findByEmail(email)
                .orElseThrow(() -> new UsernameNotFoundException(localiser.notFound("Email", email)));
    }

    @Transactional
    public String signup(String firstName, String lastName, String email, String password, UserRole userRole ) {
        boolean isUserExisting = userRepository.findByEmail(email).isPresent();
        if (isUserExisting) {
            throw new IllegalStateException(localiser.duplicate("Email", email));
        }

        String encodedPassword = bCryptPasswordEncoder.encode(password);
        User user = new User(firstName, lastName, email, encodedPassword, userRole);

        userRepository.save(user);

        String token = confirmationTokenService.generateConfirmationToken(user);
        return token;
    }

    public List<String> resendConfirmationToken(String email) {
        User user = userRepository.findByEmail(email).orElse(null);
        if (Objects.isNull(user)) {
            throw new IllegalStateException(localiser.notFound("Email", email));
        }

        String token = confirmationTokenService.generateConfirmationToken(user);
        List<String> res = new ArrayList<>();
        res.add(user.getFirstName());
        res.add(token);
        return res;
    }

    public int enableUser(String email) {
        return userRepository.updateEnabledUser(email);
    }

}
