package ua.com.university.universityspring.service;

import ua.com.university.universityspring.entity.Course;

import java.util.Optional;

public interface CourseService {

    Iterable<Course> findAll();

    Optional<Course> findById(Long id);

    void save(Course course);

    void delete(Long id);
}
