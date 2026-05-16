package by.bstu.po15.ats.web.repository;

import by.bstu.po15.ats.web.entity.PoezdTablo;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.List;
import java.util.Optional;

public interface PoezdTabloRepository extends JpaRepository<PoezdTablo,Long>
{   List<PoezdTablo> findAll();
    Optional<PoezdTablo> findById(Long id);
}
