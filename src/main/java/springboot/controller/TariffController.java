package springboot.controller;

import com.lowagie.text.DocumentException;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import springboot.dto.TariffDTO;
import springboot.entity.Tariff;
import springboot.entity.User;
import springboot.service.ServicesService;
import springboot.service.TariffService;
import springboot.service.UserService;
import springboot.service.impl.TariffSubscribingService;
import springboot.utils.TariffPDFExporter;

import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.io.IOException;
import java.security.Principal;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@Controller
public class TariffController {
    private final Logger logger = LogManager.getLogger(TariffController.class);
    private final TariffService tariffService;
    private final UserService userService;
    private final TariffSubscribingService subscribingService;
    private final ServicesService servicesService;

    @Autowired
    public TariffController(TariffService tariffService, UserService userService, TariffSubscribingService subscribingService, ServicesService servicesService) {
        this.tariffService = tariffService;
        this.userService = userService;
        this.subscribingService = subscribingService;
        this.servicesService = servicesService;
    }

    @GetMapping("/tariffs")
    public String viewTariffs(Model model) {
        model.addAttribute("tariff", new TariffDTO());
        return "tariffs";
    }

    @GetMapping("{tab}")
    public String tab(@PathVariable String tab,
                      Model model) {
        if (Arrays.asList("tab1", "tab2", "tab3")
                .contains(tab)) {
            switch (tab) {
                case "tab2":
                    model.addAttribute("tvTariffList", tariffService.getAllTariffsByType("TV"));
                    return "_" + "tab2";
                case "tab3":
                    model.addAttribute("internetTariffList", tariffService.getAllTariffsByType("Internet"));
                    return "_" + "tab3";
                default:
                    model.addAttribute("phoneTariffList", tariffService.getAllTariffsByType("Phone"));
                    return "_" + "tab1";
            }
        }
        return "tariffs";
    }

    @GetMapping("/showNewTariff")
    public String showNewTariff(@ModelAttribute("tariff") Tariff tariff, Model model) {
        model.addAttribute("serviceList", servicesService.getAllServices());
        return "new_tariff";
    }

    @PostMapping("/saveTariff")
    public String saveTariff(@Valid @ModelAttribute("tariff") TariffDTO tariffDTO,
                             BindingResult result, Model model) {
        if (result.hasErrors()) {
            model.addAttribute("serviceList", servicesService.getAllServices());
            logger.error("Something was wrong with fields of the {} tariff", tariffDTO.getName());
            return "new_tariff";
        }
        tariffService.addTariff(tariffDTO);
        logger.info("Tariff {} was successfully created", tariffDTO.getName());
        return "redirect:/tariffs";
    }

    @GetMapping("/edit_tariff/{id}")
    public String editTariff(@PathVariable(value = "id") int id, Model model) {
        model.addAttribute("tariff", tariffService.tariffById(id));
        model.addAttribute("serviceList", servicesService.getAllServices());
        return "update_tariff";
    }

    @PostMapping("/update_tariff/{id}")
    public String updateTariff(@PathVariable("id") int id, @Valid @ModelAttribute("tariff") TariffDTO tariffDTO,
                               BindingResult result, Model model) {
        if (result.hasErrors()) {
            tariffDTO.setIdTariff(id);
            model.addAttribute("serviceList", servicesService.getAllServices());
            logger.error("Something was wrong with fields of the {} tariff", tariffDTO.getName());
            return "update_tariff";
        }
        tariffService.updateTariff(id, tariffDTO);
        logger.info("Tariff {} was successfully updated", tariffDTO.getName());
        return "redirect:/tariffs";
    }

    @GetMapping("/deleteTariff/{id}")
    public String deleteTariff(@PathVariable(value = "id") int id) {
        tariffService.deleteTariff(id);
        logger.warn("Tariff with id={} was deleted", id);
        return "redirect:/tariffs";
    }

    @GetMapping("/subscribeTariff/{id}")
    public String subscribeTariff(@PathVariable(value = "id") int id, Principal principal) {
        String un = principal.getName();
        Tariff tariff = tariffService.tariffById(id);
        Optional<User> user = userService.findByEmail(un);
        if (user.get().isBlocked()) {
            logger.warn("User {} is blocked", user.get().getEmail());
            return "redirect:/tariffs?error_blocked";
        } else {
            if (user.get().getBalance() < tariff.getPrice()) {
                subscribingService.subscribe(id, un);
                logger.warn("User {} was blocked and subscribed to tariff {}", user.get().getEmail(), tariff.getName());
                return "redirect:/tariffs?error";
            } else {
                subscribingService.subscribe(id, un);
                logger.info("User {} has subscribed to tariff {}", user.get().getEmail(), tariff.getName());
                return "redirect:/tariffs?success";
            }
        }
    }

    @GetMapping("/tariffs/export/pdf")
    public void exportToPDF(HttpServletResponse response) throws DocumentException, IOException {
        response.setContentType("application/pdf");
        DateFormat dateFormatter = new SimpleDateFormat("yyyy-MM-dd_HH:mm:ss");
        String currentDateTime = dateFormatter.format(new Date());

        String headerKey = "Content-Disposition";
        String headerValue = "attachment; filename=tariffs_" + currentDateTime + ".pdf";
        response.setHeader(headerKey, headerValue);

        List<Tariff> tariffList = tariffService.getAllTariffs();

        TariffPDFExporter exporter = new TariffPDFExporter(tariffList);
        exporter.export(response);
    }
}
