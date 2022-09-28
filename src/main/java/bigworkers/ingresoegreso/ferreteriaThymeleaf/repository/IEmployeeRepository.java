package bigworkers.ingresoegreso.ferreteriaThymeleaf.repository;


import bigworkers.ingresoegreso.ferreteriaThymeleaf.entities.Employee;
import org.springframework.data.repository.CrudRepository;

public interface IEmployeeRepository extends CrudRepository<Employee, Long> {

}
