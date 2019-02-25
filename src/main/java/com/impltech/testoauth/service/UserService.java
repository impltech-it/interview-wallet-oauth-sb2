package com.impltech.testoauth.service;

import com.impltech.testoauth.domain.User;
import com.impltech.testoauth.repository.UserRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Collections;
import java.util.List;

/**
 * Created by dima.
 * Creation date 14.02.19.
 */
@Service
public class UserService implements UserDetailsService {

    private final Logger log = LoggerFactory.getLogger(UserService.class);

    private final UserRepository userRepository;

    public UserService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    /**
     * Save a user.
     *
     * @param user the entity to save
     * @return the persisted entity
     */
    public User create(User user) {
        log.debug("Request to save User : {}", user);
        return userRepository.save(user);
    }

    /**
     * Get one user by id.
     *
     * @return the entity
     */
    public List<User> findAll() {
        log.debug("Request to get all Users");
        return userRepository.findAll();
    }

    /**
     * Get one user by id.
     *
     * @param id the id of the entity
     * @return the entity
     */
    public User findOne(Long id) {
        if (id != null) {
            log.debug("Request to get Users : {}", id);
            return userRepository.getOne(id);
        }
        return null;
    }

    /**
     * Delete the user by id.
     *
     * @param id the id of the entity
     */
    public void delete(Long id) {
        if (id != null) {
            log.debug("Request to delete Users : {}", id);
            userRepository.deleteById(id);
        }
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        User user = userRepository.findByUsername(username);
        if (user == null) {
            throw new UsernameNotFoundException("Invalid username or password.");
        }
        return new org.springframework.security.core.userdetails.User(String.valueOf(user.getId()), user.getPassword(), getAuthority());
    }

    private List<SimpleGrantedAuthority> getAuthority() {
        return Collections.singletonList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }
}
