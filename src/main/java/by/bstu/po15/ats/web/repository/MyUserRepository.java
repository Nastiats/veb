package by.bstu.po15.ats.web.repository;


import by.bstu.po15.ats.web.entity.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface MyUserRepository extends JpaRepository<Users, Long>
{   Optional<Users> findByUsername(String username);
}