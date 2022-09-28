package bigworkers.ingresoegreso.ferreteriaThymeleaf.controller;

import bigworkers.ingresoegreso.ferreteriaThymeleaf.entities.DocumentType;
import bigworkers.ingresoegreso.ferreteriaThymeleaf.entities.Employee;
import bigworkers.ingresoegreso.ferreteriaThymeleaf.entities.Enterprise;
import bigworkers.ingresoegreso.ferreteriaThymeleaf.entities.Profile;
import bigworkers.ingresoegreso.ferreteriaThymeleaf.service.IDocumentTypeService;
import bigworkers.ingresoegreso.ferreteriaThymeleaf.service.IEmployeeService;
import bigworkers.ingresoegreso.ferreteriaThymeleaf.service.IEnterpriseService;
import bigworkers.ingresoegreso.ferreteriaThymeleaf.service.IProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.*;

import javax.security.auth.callback.LanguageCallback;
import javax.validation.Valid;
import java.sql.Timestamp;
import java.time.Instant;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

@Controller
public class EmployeeController {

    @Autowired
    private IEmployeeService employeeService;

    @Autowired
    private IDocumentTypeService documentTypeService;

    @Autowired
    private IProfileService profileService;

    @Autowired
    private IEnterpriseService enterpriseService;

    private final Logger LOG = Logger.getLogger(""+EmployeeController.class);

    public Timestamp date () {
        Timestamp myDate = Timestamp.from(Instant.now());
        return myDate;
    }
    @GetMapping("/employee/list")
    private String employeeList(Model model) {
        LOG.log(Level.INFO, "employeeList");
       List<Employee> empleados = employeeService.findAll();
        model.addAttribute("empleados", empleados);
        return "employees/list";
    }


    @GetMapping("/employee/create")
    public String createEmployee(Model model){
        LOG.log(Level.INFO,"createEmployee");
        Employee employee = new Employee();
        employee.setCreatedAt(date());
        model.addAttribute("employee", employee);
        List<Profile> profile = profileService.findAll();
        model.addAttribute("profile",profile);
        List<DocumentType> documentTypes = documentTypeService.findAll();
        model.addAttribute("documentTypes", documentTypes);
        List<Enterprise> enterprise = enterpriseService.findAll();
        model.addAttribute("enterprise",enterprise);
        return "/employees/registration";
    }

    @PostMapping("/employee/save")
    public String saveEmployee(@Valid Employee employee, BindingResult error, Model model){
        LOG.log(Level.INFO, "saveEmployee");
        if(employee.getDocumentType().getIdDocumentType() == 0) {
            FieldError field = new FieldError("employee", "documentType","No puede ser null");
            error.addError(field);
        }
        if(employee.getEnterprise().getIdEnterprise() == 0) {
            FieldError field = new FieldError("employee", "enterprise","No puede ser null");
            error.addError(field);
        }
        if(employee.getProfile().getIdProfile() == 0) {
            FieldError field = new FieldError("employee", "profile","No puede ser null");
            error.addError(field);
        }
        for(ObjectError e : error.getAllErrors())
            System.out.println(e.toString());
        if(error.hasErrors()) {
            List<Profile> profile = profileService.findAll();
            model.addAttribute("profile",profile);
            List<DocumentType> documentTypes = documentTypeService.findAll();
            model.addAttribute("documentTypes", documentTypes);
            List<Enterprise> enterprise = enterpriseService.findAll();
            model.addAttribute("enterprise",enterprise);
            return "employees/registration";
        }
        employee.setState(true);
        employee.setUpdatedAt(date());
        employee = employeeService.createEmployee(employee);
        return "redirect:/employee/list";
    }

    @RequestMapping(value = "/employee/edit/{id}", method = RequestMethod.GET)
    public  String editEmployee(@PathVariable("id")long idEmployee, Model model){
        LOG.log(Level.INFO, "editEmployee");
        Employee employee = employeeService.findById(idEmployee);
        model.addAttribute("employee", employee);
        List<DocumentType> documentTypes = documentTypeService.findAll();
        model.addAttribute("documentTypes", documentTypes);
        List<Enterprise> enterprise = enterpriseService.findAll();
        model.addAttribute("enterprise",enterprise);
        List<Profile> profile = profileService.findAll();
        model.addAttribute("profile",profile);
        return "/employees/registration";
    }

    @RequestMapping(value = "/employee/delete/{id}", method = RequestMethod.GET)
    public String deleteEmployee(@PathVariable("id") long idEmployee, Model modelo) {
        LOG.log(Level.INFO, "deleteEmployee");
        //employeeService.deleteEmployee(id);
        Employee employee = employeeService.findById(idEmployee);
        employee.setState(false);
        employee.setUpdatedAt(date());
        employee = employeeService.updateEmployee(idEmployee,employee);
        return "redirect:/employee/list";
    }



}
