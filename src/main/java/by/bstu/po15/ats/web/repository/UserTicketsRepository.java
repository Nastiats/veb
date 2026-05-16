package by.bstu.po15.ats.web.repository;

import by.bstu.po15.ats.web.entity.UserTickets;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface UserTicketsRepository extends JpaRepository<UserTickets,Long>
{   List<UserTickets> findAll();
}
