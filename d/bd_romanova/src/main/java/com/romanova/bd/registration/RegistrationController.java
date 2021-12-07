package com.romanova.bd.registration;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@AllArgsConstructor
public class RegistrationController {

    private final RegistrationService registrationService;

    @GetMapping("/register")
    public String getRegisterPage(Model model){
        model.addAttribute("request", new RegistrationRequest());
        return "registration/register_form";
    }

    @PostMapping("/register")
    public String register( @ModelAttribute("request") RegistrationRequest request, Model model){

        if(registrationService.checkIsUniqueLogin(request.getLogin())){
            model.addAttribute("not_unique_login", "true");
            return "registration/register_form";
        }

        registrationService.register(request);

        return  "redirect:/login";
    }
}
