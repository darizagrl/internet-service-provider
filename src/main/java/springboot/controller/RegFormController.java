package springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import springboot.entity.User;
import springboot.service.UserService;
import springboot.dto.UserDTO;

import javax.validation.Valid;

@Controller
@RequestMapping("/registration")
public class RegFormController {
    @Autowired
    private UserService userService;

//    public RegFormController(UserService userService) {
//        this.userService = userService;
//    }

    @ModelAttribute("user")
    public UserDTO userDTO() {
        return new UserDTO();
    }

    @GetMapping
    public String registration(Model model) {
        model.addAttribute("user", new UserDTO());
        return "registration";
    }

    @PostMapping()
    public String registerUserAccount(@ModelAttribute("user") @Valid UserDTO userDTO) {
        userService.save(userDTO);
        return "redirect:/registration?success";
    }
}
