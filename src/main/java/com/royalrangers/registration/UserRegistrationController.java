package com.royalrangers.registration;

import com.royalrangers.model.User;
import com.royalrangers.registration.validators.Validator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.validation.Valid;

@Controller
public class UserRegistrationController {
    @Autowired
    private UserService userService;

    @Autowired
    private Validator userValidator;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(Model model) {
        model.addAttribute("userForm", new User());
        return "/registration";
    }

    @RequestMapping(value = "/registration", method = RequestMethod.POST)
    public String registration(@Valid @RequestBody UserForm userForm, BindingResult bindingResult) {

        userValidator.validate(userForm, bindingResult);

        if (bindingResult.hasErrors()) {
            return "/registration";
        }

        User user = new User();
        user.setUsername(userForm.getUsername());
        user.setFirstname(userForm.getFirstname());
        user.setLastname(userForm.getLastname());
        user.setEmail(userForm.getEmail());
        user.setPassword(passwordEncoder.encode(userForm.getPassword()));

        userService.save(user);

        return "redirect:/welcome";
    }

}
