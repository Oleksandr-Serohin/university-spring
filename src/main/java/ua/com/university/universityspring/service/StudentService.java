package ua.com.university.universityspring.service;

import ua.com.university.universityspring.entity.Student;

import java.util.Optional;

public interface StudentService {

    Iterable<Student> findAll();

    Optional<Student> findById(Long id);

    void save(Optional<Student> student);

    void delete(Long id);

    Iterable<Student> searchBy(String theName);
}
