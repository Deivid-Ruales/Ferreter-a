package bigworkers.ingresoegreso.ferreteriaThymeleaf.repository;


import bigworkers.ingresoegreso.ferreteriaThymeleaf.entities.Transaction;
import org.springframework.data.repository.CrudRepository;

public interface ITransactionRepository extends CrudRepository<Transaction, Long> {
}
