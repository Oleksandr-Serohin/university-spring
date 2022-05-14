package ua.com.university.universityspring.service;

import org.springframework.security.core.userdetails.UserDetailsService;
import ua.com.university.universityspring.entity.User;

public interface UserService extends UserDetailsService {

    Iterable<User> findAll();

    boolean save(User user);

    void delete(Long id);
}
