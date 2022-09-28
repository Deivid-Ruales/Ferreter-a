package bigworkers.ingresoegreso.ferreteriaThymeleaf.repository;


import bigworkers.ingresoegreso.ferreteriaThymeleaf.entities.Profile;
import org.springframework.data.repository.CrudRepository;

public interface IProfileRepository extends CrudRepository<Profile, Long> {
}
