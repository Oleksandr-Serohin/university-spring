package ua.com.university.universityspring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.com.university.universityspring.entity.Course;

@Repository
public interface CourseRepository extends CrudRepository <Course, Long> {
}
