package springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import springboot.dto.UserDTO;
import springboot.entity.User;
import springboot.service.UserService;

import javax.validation.Valid;
import java.util.Optional;

@Controller
@RequestMapping("/registration")
public class RegFormController {
    private final UserService userService;

    @Autowired
    public RegFormController(UserService userService) {
        this.userService = userService;
    }

    @GetMapping
    public String registration(Model model) {
        model.addAttribute("user", new UserDTO());
        return "registration";
    }

    @PostMapping()
    public String registerUserAccount(@ModelAttribute("user") @Valid UserDTO userDTO, BindingResult result) {
        Optional<User> existing = userService.findByEmail(userDTO.getEmail());
        if (existing.isPresent()) {
            result.rejectValue("email", null, "There is already an account registered with that email");
        }
        if (result.hasErrors()) {
            return "registration";
        }
        userService.save(userDTO);
        return "redirect:/registration?success";
    }
}
