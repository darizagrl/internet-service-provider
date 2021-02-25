package springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import springboot.entity.User;
import springboot.service.TariffService;
import springboot.service.impl.TariffSubscribingService;
import springboot.service.UserService;

import javax.validation.Valid;
import java.security.Principal;
import java.util.Optional;

@Controller
public class AccountController {
    private final TariffService tariffService;
    private final UserService userService;
    private final TariffSubscribingService subscribingService;

    @Autowired
    public AccountController(TariffService tariffService, UserService userService, TariffSubscribingService subscribingService) {
        this.tariffService = tariffService;
        this.userService = userService;
        this.subscribingService = subscribingService;
    }

    @GetMapping("/account")
    public String account(Model model, Principal principal) {
        String un = principal.getName();
        Optional<User> user = userService.findByEmail(un);
        model.addAttribute("user", user);
        return "account";
    }

    @PostMapping("/account")
    public String accountReplenishment(@AuthenticationPrincipal @ModelAttribute User user, @Valid Model model, Principal principal) {
        String un = principal.getName();
        model.addAttribute("user", user);
        userService.updateUserBalance(un, user.getBalance());
        return "redirect:/account";
    }

    @GetMapping("/unsubscribeTariff/{id}")
    public String unsubscribeTariff(@PathVariable(value = "id") int id, Principal principal) {
        String un = principal.getName();
        tariffService.tariffById(id);
        subscribingService.unsubscribe(id, un);
        return "redirect:/account?success";
    }
}