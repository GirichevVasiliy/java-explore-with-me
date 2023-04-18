package ru.practicum.users.storage;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;
import ru.practicum.users.model.User;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
}
