package by.bstu.po15.ats.web.repository;

import by.bstu.po15.ats.web.entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

// Интерфейс для загрузки из базы объектов типа User
public interface UserRepository extends JpaRepository<User,Long>
{    User findByEmail(String email);
}