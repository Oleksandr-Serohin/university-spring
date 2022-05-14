package ua.com.university.universityspring.service;

import org.springframework.stereotype.Service;
import ua.com.university.universityspring.entity.Group;
import ua.com.university.universityspring.repository.GroupRepository;

import java.util.Optional;

@Service
public record GroupServiceImp(
        GroupRepository groupRepository) implements GroupService {

    @Override
    public Iterable<Group> findAll() {
        return groupRepository.findAll();
    }

    @Override
    public Optional<Group> findById(Long id) {
        return Optional.empty();
    }

    @Override
    public void save(Group course) {

    }

    @Override
    public void delete(Long id) {

    }
}
