package com.frailty.backend.repository;

import com.frailty.backend.entity.AppUser;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AppUserRepository extends JpaRepository<AppUser, Long> {
    Optional<AppUser> findByEmail(String email);

    @Modifying
    @Query("UPDATE AppUser a " +
            "SET a.isEnabled = TRUE WHERE a.email = ?1")
    int updateEnabledUser(String email);
}
