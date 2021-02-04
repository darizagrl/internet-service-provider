package springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;
import springboot.repository.UserRepo;
import springboot.service.TariffService;

import java.security.Principal;

@Controller
public class AccountController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private TariffService tariffService;

    @RequestMapping(value = "/account")
    public String account(Model model, Principal principal) {
        String un = principal.getName();
        model.addAttribute("user", userRepo.findByEmail(un));
        model.addAttribute("tariffList", tariffService.getAllTariffs());
        return "account";
    }
}