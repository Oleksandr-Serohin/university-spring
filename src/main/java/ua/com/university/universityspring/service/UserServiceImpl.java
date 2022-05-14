package ua.com.university.universityspring.service;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import ua.com.university.universityspring.config.EncryptionConfig;
import ua.com.university.universityspring.entity.Role;
import ua.com.university.universityspring.entity.User;
import ua.com.university.universityspring.repository.UserRepository;

import java.util.Collections;
import java.util.Optional;
import java.util.UUID;

@Service
public record UserServiceImpl(UserRepository userRepository) implements UserService {
    private static final boolean  IS_ACTIVE_USER = true;

    @Override
    public Iterable<User> findAll() {
        return userRepository.findAll();
    }

    @Override
    public boolean save(User user) {
        User userFromDb = userRepository.findByUsername(user.getUsername());

        if (userFromDb != null) {
            return false;
        }

        user.setActive(true);
        user.setRoles(Collections.singleton(Role.ADMIN));
        user.setActivationCode(UUID.randomUUID().toString());
        user.setPassword(EncryptionConfig.getPasswordEncoder().encode(user.getPassword()));
        userRepository.save(user);
        return true;
    }

    @Override
    public void delete(Long id) {
        Optional<User> dbSUser = userRepository.findById(id);
        dbSUser.ifPresent(userRepository::delete);
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
    Optional<User> dbUser = Optional.ofNullable(userRepository.findByUsername(username));
    if(dbUser.isEmpty()){
            throw new UsernameNotFoundException("Invalid username or password.");
    }
    User user = dbUser.get();
    return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), user.getRoles());
    }
}
