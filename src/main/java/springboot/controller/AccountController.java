package springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import springboot.entity.User;
import springboot.repository.UserRepo;
import springboot.service.TariffService;
import springboot.service.TariffSubscribingService;
import springboot.service.UserService;

import java.security.Principal;

@Controller
public class AccountController {
    @Autowired
    private UserRepo userRepo;
    @Autowired
    private TariffService tariffService;
    @Autowired
    private UserService userService;
    @Autowired
    private TariffSubscribingService subscribingService;

    @GetMapping("/account")
    public String account(@ModelAttribute("user") User user, Model model, Principal principal) {
        String un = principal.getName();
        model.addAttribute("balance", userService.getUserBalance(un));
        model.addAttribute("user", userRepo.findByEmail(un));
        model.addAttribute("tariffList", userService.getUserTariffs(un));
        return "account";
    }

    @PostMapping("/account")
    public String accountReplenishment(@ModelAttribute("user") User user, Model model, Principal principal) {
        String un = principal.getName();
        model.addAttribute("balance", userService.getUserBalance(un));
        model.addAttribute("user", userRepo.findByEmail(un));
        userService.updateUserBalance(un, user.getBalance());
        return "redirect:/account?success";
    }

    @GetMapping("/unsubscribeTariff/{id}")
    public String unsubscribeTariff(@PathVariable(value = "id") int id, Principal principal) {
        String un = principal.getName();
        this.tariffService.tariffById(id);
        subscribingService.unsubscribe(id, un);
        return "redirect:/account?success";
    }
}