package bigworkers.ingresoegreso.ferreteriaThymeleaf.service;



import bigworkers.ingresoegreso.ferreteriaThymeleaf.entities.Enterprise;

import java.util.List;

public interface IEnterpriseService {

    public Enterprise findById(long id);

    public List<Enterprise> findAll();

    public Enterprise createEnterprise(Enterprise empresa);

    public Enterprise updateEnterprise(long id, Enterprise empresa);

    public void deleteEnterprise(long id);
}
