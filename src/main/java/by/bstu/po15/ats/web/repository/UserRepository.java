package by.bstu.po15.ats.web.repository;

import by.bstu.po15.ats.web.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;


public interface UserRepository extends JpaRepository<User,Long> {

    User findByEmail(String email);
}