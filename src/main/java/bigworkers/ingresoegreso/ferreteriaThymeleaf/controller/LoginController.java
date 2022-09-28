package bigworkers.ingresoegreso.ferreteriaThymeleaf.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import java.util.logging.Logger;

@Controller
public class LoginController {

    private final Logger LOG = Logger.getLogger(""+LoginController.class);

    @GetMapping("/login")
    public String login(){
        return "/login";
    }
}
