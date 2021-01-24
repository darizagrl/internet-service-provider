package springboot.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {
    @GetMapping("/")
    public String showHome(Model model) {
        return "index";
    }

    @GetMapping("/index")
    public String showIndex(Model model) {
        return "index";
    }

    @GetMapping("/main")
    public String showMain(Model model) {
        return "main";
    }

    @GetMapping("/reg_form")
    public String logout(Model model) {
        return "registration";
    }
}
