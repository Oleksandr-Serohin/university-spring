package ua.com.university.universityspring.repository;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import ua.com.university.universityspring.entity.Student;

@Repository
public interface StudentRepository extends CrudRepository<Student, Long> {

    Iterable<Student> findAllByOrderByLastNameAsc();

    Iterable<Student> findByFirstNameContainsOrLastNameContainsAllIgnoreCase(String name, String lName);
}
