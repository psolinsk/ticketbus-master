package pl.edu.wszib.ticketbus.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import pl.edu.wszib.ticketbus.model.User;
import pl.edu.wszib.ticketbus.model.view.RegistrationModel;
import pl.edu.wszib.ticketbus.services.IUserService;
import pl.edu.wszib.ticketbus.session.SessionObject;

import javax.annotation.Resource;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Controller
public class UserController {

    @Autowired
    IUserService userService;

    @Resource
    SessionObject sessionObject;

    @RequestMapping(value = "/account", method = RequestMethod.GET)
    public String accountInfo(Model model){
        if(!this.sessionObject.isLogged()) {
            return "redirect:/login";
        }

        model.addAttribute("accountInfo", this.sessionObject.getLoggedUser());
        model.addAttribute("isLogged", this.sessionObject.isLogged());
        model.addAttribute("info", this.sessionObject.getInfo());
        return "account";
    }

    @RequestMapping(value = "/login", method = RequestMethod.GET)
    public String loginForm(Model model) {
        if(this.sessionObject.isLogged()) {
            return "redirect:/main";
        }
        model.addAttribute("userModel", new User());
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String login(@ModelAttribute User user) {
        this.userService.authenticate(user);
        if(this.sessionObject.isLogged()) {
            return "redirect:/main";
        }
        return "redirect:/login";
    }

    @RequestMapping(value = "/logout", method = RequestMethod.GET)
    public String logout() {
        this.userService.logout();
        return "redirect:/login";
    }

    @RequestMapping(value = "/register", method = RequestMethod.GET)
    public String registerForm(Model model) {
        model.addAttribute("registrationModel", new RegistrationModel());
        model.addAttribute("info", this.sessionObject.getInfo());
        return "register";
    }

    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public String register(@ModelAttribute RegistrationModel registrationModel) {
        Pattern regexp = Pattern.compile("[A-Za-z0-9]{5}.*");
        Matcher loginMatcher = regexp.matcher(registrationModel.getLogin());
        Matcher passMatcher = regexp.matcher(registrationModel.getPassword());
        Matcher pass2Matcher = regexp.matcher(registrationModel.getPassword2());

        if(!loginMatcher.matches() || !passMatcher.matches() || !pass2Matcher.matches() || !registrationModel.getPassword().equals(registrationModel.getPassword2())) {
            this.sessionObject.setInfo("rózne hasła!");
            return "redirect:/register";
        }

        if(this.userService.register(registrationModel)) {
            return "redirect:/login";
        } else {
            this.sessionObject.setInfo("login zajęty!");
            return "redirect:/register";
        }
    }
}
