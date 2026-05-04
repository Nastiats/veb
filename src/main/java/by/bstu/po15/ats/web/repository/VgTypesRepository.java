package by.bstu.po15.ats.web.repository;

import by.bstu.po15.ats.web.entity.VgTypes;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import java.util.Optional;

public interface VgTypesRepository extends JpaRepository<VgTypes,Long>
{

}
