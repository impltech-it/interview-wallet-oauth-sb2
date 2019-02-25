package com.impltech.testoauth.repository;

import com.impltech.testoauth.domain.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by dima.
 * Creation date 14.02.19.
 */
public interface UserRepository extends JpaRepository<User, Long> {

    User findByUsername(String username);
}
