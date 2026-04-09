package by.bstu.po15.ats.web.repository;

import by.bstu.po15.ats.web.entity.UsersInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;


@Repository
public interface UserRepository extends JpaRepository<UsersInfo, String>
{
}