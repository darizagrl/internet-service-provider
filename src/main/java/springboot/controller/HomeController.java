package springboot.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import springboot.dto.UserDTO;
import springboot.entity.User;
import springboot.service.UserService;

import java.util.List;

@Controller
public class HomeController {
    @GetMapping("/")
    public String showHome() {
        return "index";
    }

    @GetMapping("/index")
    public String showIndex() {
        return "index";
    }

    @Autowired
    private UserService userService;

    @GetMapping("/main")
    public String showMain(Model model) {
        return findPaginated(1, model);
    }

    @GetMapping("/showNewUserForm")
    public String showNewUserForm() {
        return "redirect:/registration";
    }

//    @GetMapping("/showFormForUpdate/{id}")
//    public String showFormForUpdate(@PathVariable(value = "id") int id, Model model, UserDTO userDTO) {
//        // get user from the service
//        userService.getUserById(userDTO.getId());
//        // set user as a model attribute to pre-populate the form
////        model.addAttribute("user", user);
//        userService.save(userDTO);
//        return "update_user";
//    }

    @GetMapping("/deleteUser/{id}")
    public String deleteUser(@PathVariable(value = "id") int id) {
        // call delete user method
        this.userService.deleteUserById(id);
        return "redirect:/main";
    }

    @GetMapping("/page/{pageNo}")
    public String findPaginated(@PathVariable(value = "pageNo") int pageNo, Model model) {
        int pageSize = 5;
        Page<User> page = userService.findPaginated(pageNo, pageSize);
        List<User> listUsers = page.getContent();
        model.addAttribute("currentPage", pageNo);
        model.addAttribute("totalPages", page.getTotalPages());
        model.addAttribute("totalItems", page.getTotalElements());
        model.addAttribute("listUsers", listUsers);
        return "main";
    }
}
