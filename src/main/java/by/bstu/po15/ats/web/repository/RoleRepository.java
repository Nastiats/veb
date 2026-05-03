package by.bstu.po15.ats.web.repository;

import by.bstu.po15.ats.web.entity.Role;
import org.springframework.data.jpa.repository.JpaRepository;

// Интерфейс для загрузки из базы объектов типа Role
public interface RoleRepository extends JpaRepository<Role,Long>
{    Role findByName(String name);
}
