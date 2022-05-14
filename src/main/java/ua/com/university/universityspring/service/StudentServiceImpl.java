package ua.com.university.universityspring.service;

import org.springframework.stereotype.Service;
import ua.com.university.universityspring.entity.Student;
import ua.com.university.universityspring.repository.StudentRepository;

import java.util.Optional;

@Service
public record StudentServiceImpl(StudentRepository studentRepository) implements StudentService {

    @Override
    public Iterable<Student> findAll() {
        return studentRepository.findAllByOrderByLastNameAsc();
    }

    @Override
    public Optional<Student> findById(Long theId) {
        Optional<Student> student_DB = studentRepository.findById(theId);
        if (student_DB.isEmpty()) {
            throw new RuntimeException("Did not find student id - " + theId);
        }
        return student_DB;

    }

    @Override
    public void save(Optional<Student> student) {
        student.ifPresent(studentRepository::save);
    }

    @Override
    public void delete(Long id) {
        Optional<Student> dbStudent = studentRepository.findById(id);
        dbStudent.ifPresent(studentRepository::delete);
    }

    @Override
    public Iterable<Student> searchBy(String theName) {
        Iterable<Student> students;
        if (theName != null && (theName.trim().length() > 0)) {
            students = studentRepository.findByFirstNameContainsOrLastNameContainsAllIgnoreCase(theName, theName);
        }else {
            students = studentRepository.findAll();
        }
        return students;
    }
}
