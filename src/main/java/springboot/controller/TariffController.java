package springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import springboot.dto.TariffDTO;
import springboot.entity.Tariff;
import springboot.repository.TariffRepo;
import springboot.repository.UserRepo;
import springboot.service.TariffService;
import springboot.service.TariffSubscribingService;

import java.security.Principal;
import java.util.Arrays;
import java.util.List;

@Controller
public class TariffController {
    @Autowired
    TariffService tariffService;
    @Autowired
    TariffRepo tariffRepo;
    @Autowired
    UserRepo userRepo;
    @Autowired
    TariffSubscribingService subscribingService;

    @GetMapping("/tariffs")
    public String viewTariffs(Model model) {
        model.addAttribute("tariff", new TariffDTO());
        return "tariffs";
    }

    @GetMapping("{tab}")
    public String tab(@PathVariable String tab, Model model) {
        if (Arrays.asList("tab1", "tab2", "tab3")
                .contains(tab)) {
            model.addAttribute("phoneTariffList", tariffService.getAllTariffsByType("Phone"));
            model.addAttribute("tvTariffList", tariffService.getAllTariffsByType("TV"));
            model.addAttribute("internetTariffList", tariffService.getAllTariffsByType("Internet"));
            return "_" + tab;
        }
        return "tariffs";
    }

    @GetMapping("/showNewTariff")
    public String showNewTariff(Model model) {
        Tariff tariff = new Tariff();
        model.addAttribute("tariff", tariff);
        return "new_tariff";
    }

    @PostMapping("/saveTariff")
    public String saveTariff(@ModelAttribute("tariff") TariffDTO tariffDTO, BindingResult result) {
        tariffService.addTariff(tariffDTO);
        return "redirect:/tariffs";
    }

    @GetMapping("/showFormForTariffUpdate/{id}")
    public String showFormForTariffUpdate(@PathVariable(value = "id") int id, Model model) {
        Tariff tariff = tariffService.tariffById(id);
        model.addAttribute("tariff", tariff);
        return "update_tariff";
    }

    @GetMapping("/deleteTariff/{id}")
    public String deleteTariff(@PathVariable(value = "id") int id) {
        // call delete user method
        this.tariffService.deleteTariff(id);
        return "redirect:/tariffs";
    }

    @GetMapping("/subscribeTariff/{id}")
    public String subscribeTariff(@PathVariable(value = "id") int id, Principal principal) {
        String un = principal.getName();
        this.tariffService.tariffById(id);
        subscribingService.subscribe(id, un);
        return "redirect:/tariffs?success";
    }

    @GetMapping("/subscribeTariff/{id}?error")
    public String subscribeTariffError(@PathVariable(value = "id") int id, Principal principal) {
        String un = principal.getName();
        this.tariffService.tariffById(id);
        subscribingService.subscribe(id, un);
        return "redirect:/tariffs?error";
    }
}
