package bigworkers.ingresoegreso.ferreteriaThymeleaf.controller;

import bigworkers.ingresoegreso.ferreteriaThymeleaf.entities.Enterprise;
import bigworkers.ingresoegreso.ferreteriaThymeleaf.service.EnterpriseService;
import bigworkers.ingresoegreso.ferreteriaThymeleaf.service.IEnterpriseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.validation.Validator;
import org.springframework.web.bind.annotation.*;

import javax.persistence.Id;
import javax.validation.Valid;

import java.sql.Timestamp;
import java.time.Instant;
import java.sql.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;


@Controller
public class EnterpriseController {

    @Autowired
    private IEnterpriseService enterpriseService;

    private final Logger LOG = Logger.getLogger(""+EnterpriseController.class);
    private Validator validator;

    public Timestamp date () {
        Timestamp myDate = Timestamp.from(Instant.now());
        return myDate;
    }
    /*public Date date2 () {
        long miliseconds = System.currentTimeMillis();
        Date date = new Date(miliseconds);
        System.out.println(date);
        return date;
    }*/

    @GetMapping("/enterprise/list")
    public String getListEnterprise(Model model){
        LOG.log(Level.INFO,"getListEnterprise");
        List<Enterprise> enterprises = enterpriseService.findAll();
        model.addAttribute("enterprises", enterprises);
        return "enterprises/list";
    }
    @GetMapping("/enterprise/crear")
    public String createEnterprise(Model model) {
        LOG.log(Level.INFO, "createEnterprise");
        //Enterprise
        Enterprise enterprise = new Enterprise();
        enterprise.setCreatedAt(date());
        model.addAttribute("enterprise", enterprise);
        return "enterprises/registration";
    }
    @PostMapping ("/enterprise/guardar")
    public String guardarEnterprise( @Valid Enterprise empresa, BindingResult error, Model modelo) {
        LOG.log(Level.INFO, "guardarEnterprise");
        for(ObjectError e : error.getAllErrors())
            System.out.println(e.toString());
        if(error.hasErrors()) {
            return "enterprises/registration";
        }
        empresa.setState(true);
        empresa.setUpdatedAt(date());
        empresa = enterpriseService.createEnterprise(empresa);
        return "redirect:/enterprise/list";
    }

    @RequestMapping(value = "/enterprise/editar/{id}", method = RequestMethod.GET)
    public String editEnterprise(@PathVariable("id") long id, Model modelo){
        LOG.log(Level.INFO,"editEnterprise");
        Enterprise enterprise  = enterpriseService.findById(id);
        modelo.addAttribute("enterprise", enterprise);
        return "enterprises/registration";
    }

    @RequestMapping(value = "/enterprise/eliminar/{id}", method = RequestMethod.GET)
    public String deleteEnterprise(@PathVariable("id") long id, Model modelo) {
        LOG.log(Level.INFO, "deleteEnterprise");
        //enterpriseService.deleteEnterprise(id);
        Enterprise enterprises = enterpriseService.findById(id);
        enterprises.setState(false);
        enterprises.setUpdatedAt(date());
        enterpriseService.updateEnterprise(id,enterprises);
        return "redirect:/enterprise/list";
    }
}
