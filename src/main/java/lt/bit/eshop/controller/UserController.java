package lt.bit.eshop.controller;

import javassist.NotFoundException;
import lt.bit.eshop.form.UserModel;
import lt.bit.eshop.service.UserService;
import org.omg.CosNaming.NamingContextPackage.NotFound;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;
import java.util.Collection;
import java.util.stream.Collectors;

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
            try {

                userService.createNewUser(userModel);
            } catch(Exception e) {

                System.out.println("Duplicate username");
            }
            return "redirect:/login";
        }

        java.lang.Integer Java = 2;
        return "user/registration-form";
    }
}
