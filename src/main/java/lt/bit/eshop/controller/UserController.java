package lt.bit.eshop.controller;

import lt.bit.eshop.form.UserModel;
import lt.bit.eshop.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
@RequestMapping("/users")
public class UserController {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/registration")
    public String registrationForm(Model model) {
        model.addAttribute("userModel", new UserModel());
        return "user/registration-form";
    }


    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    private String creteUser(@Valid @ModelAttribute UserModel userModel, BindingResult bindingResult, Model model) {

        if (!bindingResult.hasErrors()) {
            userService.createNewUser(userModel);
            return "redirect:/login";
        }
        return "user/registration-form";
    }
}
