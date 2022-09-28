package bigworkers.ingresoegreso.ferreteriaThymeleaf.controller;

import bigworkers.ingresoegreso.ferreteriaThymeleaf.entities.*;
import bigworkers.ingresoegreso.ferreteriaThymeleaf.service.EnterpriseService;
import bigworkers.ingresoegreso.ferreteriaThymeleaf.service.IEmployeeService;
import bigworkers.ingresoegreso.ferreteriaThymeleaf.service.IEnterpriseService;
import bigworkers.ingresoegreso.ferreteriaThymeleaf.service.ITransactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;




@Controller
public class TransactionController {

    @Autowired
    private ITransactionService TransactionService;
    @Autowired
    private IEmployeeService EmployeeService;

    @Autowired
    private IEnterpriseService EnterpriseService;
    private final Logger LOG = Logger.getLogger(""+TransactionController.class);

    public Timestamp date () {
        Timestamp myDate = Timestamp.from(Instant.now());
        return myDate;
    }
    @GetMapping("/transaction/list")
    private String getListTransaction(Model model){
        LOG.log(Level.INFO, "getListTransaction");
        List<Transaction> transacciones = TransactionService.findAll();
        for (Transaction transaccion : transacciones)
            System.out.println(transaccion.toString());
        model.addAttribute( "transacciones",transacciones);
        return "/transactions/list";
    }
    @GetMapping("/transaction/create")
    private String createTransaction(Model modelo) {
        LOG.log(Level.INFO, "createTransaction");
        //visualizar la transaccion
        Transaction transaction = new Transaction();
        transaction.setCreatedAt(date());
        modelo.addAttribute("transaction",transaction);
        //visualizar el empleado
        List<Employee> empleados= EmployeeService.findAll();
        modelo.addAttribute("empleados",empleados);
        List<Enterprise> empresas = EnterpriseService.findAll();
        modelo.addAttribute("empresas", empresas);
        return "/transactions/registration";

    }

    @PostMapping("/transaction/save")
    public String guardarTransaccion (Transaction transaccion){
        LOG.log(Level.INFO, "guardarTransaccion");
        transaccion.setState(true);
        transaccion.setUpdatedAt(date());
        System.out.println(transaccion.toString());
        transaccion = TransactionService.CreateTransaction(transaccion);
        return "redirect:/transaction/list";
    }

    @RequestMapping(value = "/transaction/edit/{id}", method = RequestMethod.GET)
    public  String editTransaction(@PathVariable("id")long id, Model model){
        LOG.log(Level.INFO, "editTransaction");
        Transaction transaction = TransactionService.findById(id);
        model.addAttribute("transaction", transaction);
        List<Enterprise> empresas = EnterpriseService.findAll();
        model.addAttribute("empresas",empresas);
        List<Employee> empleados = EmployeeService.findAll();
        model.addAttribute("empleados",empleados);
        return "/transactions/registration";
    }
    @RequestMapping(value = "/transaction/delete/{id}", method = RequestMethod.GET)
    public String deleteTransaction(@PathVariable ("id") long id , Model modelo){
         LOG.log(Level.INFO, "deleteTransaction");
        //TransactionService.deleteTransaction(id);
        Transaction transaction = TransactionService.findById(id);
        transaction.setState(false);
        transaction.setUpdatedAt(date());
        transaction = TransactionService.updateTransaction(id,transaction);
        return "redirect:/transaction/list";
    }


}

