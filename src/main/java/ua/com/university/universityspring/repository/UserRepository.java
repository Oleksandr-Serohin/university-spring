package ua.com.university.universityspring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.com.university.universityspring.entity.User;

@Repository
public interface UserRepository extends CrudRepository <User, Long> {
    User findByUsername(String username);
}
