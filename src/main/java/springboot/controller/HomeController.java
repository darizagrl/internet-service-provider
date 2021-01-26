package springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import springboot.entity.User;
import springboot.service.UserService;

@Controller
public class HomeController {
    @Autowired
    private UserService userService;

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
        model.addAttribute("listUsers", userService.getAllUsers());
        return "main";
    }

    @GetMapping("/showNewUserForm")
    public String showNewUserForm(Model model) {
        User user = new User();
        model.addAttribute("user", user);
        return "new_user";
    }
    @PostMapping("/saveUser")
    public String saveUser(@ModelAttribute("user") User user) {
        // save user to database
        userService.saveUser(user);
        return "redirect:/main";
    }
    @GetMapping("/showFormForUpdate/{id}")
    public String showFormForUpdate(@PathVariable(value = "id") int id, Model model) {

        // get user from the service
        User user = userService.getUserById(id);

        // set user as a model attribute to pre-populate the form
        model.addAttribute("user", user);
        return "update_user";
    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable(value = "id") int id) {

        // call delete user method
        this.userService.deleteUserById(id);
        return "redirect:/main";
    }
}
