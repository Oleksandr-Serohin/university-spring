package ua.com.university.universityspring.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ua.com.university.universityspring.entity.Group;

@Repository
public interface GroupRepository extends JpaRepository<Group, Long> {
}
