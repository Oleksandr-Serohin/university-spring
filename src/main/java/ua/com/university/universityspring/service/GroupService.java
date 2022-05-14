package ua.com.university.universityspring.service;

import ua.com.university.universityspring.entity.Group;

import java.util.Optional;

public interface GroupService {

    Iterable<Group> findAll();

    Optional<Group> findById(Long id);

    void save(Group course);

    void delete(Long id);

}
