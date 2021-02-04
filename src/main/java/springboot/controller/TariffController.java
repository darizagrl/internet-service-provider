package springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import springboot.dto.TariffDTO;
import springboot.entity.Tariff;
import springboot.repository.TariffRepo;
import springboot.service.TariffService;

import java.util.Arrays;

@Controller
public class TariffController {
    @Autowired
    TariffService tariffService;
    @Autowired
    TariffRepo tariffRepo;

    @GetMapping("/tariffs")
    public String viewTariffs(Model model) {
        model.addAttribute("tariff", new TariffDTO());
        return "tariffs";
    }

    @GetMapping("{tab}")
    public String tab(@PathVariable String tab, Model model) {
        if (Arrays.asList("tab1", "tab2", "tab3")
                .contains(tab)) {
            model.addAttribute("phoneTariffList", tariffService.getAllTariffsByType("phone"));
            model.addAttribute("tvTariffList", tariffService.getAllTariffsByType("TV"));
            model.addAttribute("internetTariffList", tariffService.getAllTariffsByType("Internet"));
            return "_" + tab;
        }
        return "index";
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
    public String subscribeTariff(@PathVariable(value = "id") int id) {
        this.tariffService.tariffById(id);
        return "redirect:/tariffs";
    }

//    @GetMapping("/tariffs")
//    public String showTariffs(Model model) {
//        return findTariffsPaginated(1, model);
//    }

//    @GetMapping("/page/{pageNo}")
//    public String findTariffsPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
//        int pageSize = 5;
//        Page<Tariff> page = tariffService.findPaginated(pageNo, pageSize);
//        List<Tariff> tariffList = page.getContent();
//        model.addAttribute("currentPage", pageNo);
//        model.addAttribute("totalPages", page.getTotalPages());
//        model.addAttribute("totalItems", page.getTotalElements());
//        model.addAttribute("tariffList", tariffList);
//        return "tariffs";
//    }
}
