package ua.com.university.universityspring.service;

import org.springframework.stereotype.Service;
import ua.com.university.universityspring.entity.Course;
import ua.com.university.universityspring.repository.CourseRepository;

import java.util.Optional;

@Service
public record CourseServiceImpl(CourseRepository courseRepository) implements CourseService {

    @Override
    public Iterable<Course> findAll() {
        return courseRepository.findAll();
    }

    @Override
    public Optional<Course> findById(Long id) {
        return courseRepository.findById(id);
    }

    @Override
    public void save(Course course) {
       courseRepository.save(course);
    }

    @Override
    public void delete(Long id) {
        Optional<Course> dbCourse = courseRepository.findById(id);
        dbCourse.ifPresent(courseRepository::delete);
    }
}
