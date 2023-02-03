package com.example.fastcampusboard.controller;

import com.example.fastcampusboard.domain.UserAccount;
import com.example.fastcampusboard.dto.request.UserForm;
import com.example.fastcampusboard.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

@Controller
@RequiredArgsConstructor
public class UserController {
    private final UserAccountService userAccountService;

    @GetMapping("signUp")
    public String signUp(Model model){
        model.addAttribute("userForm", new UserForm());
        return "signUp";
    }

    @PostMapping("signUp")
    public String signUpRequest(@ModelAttribute UserForm userForm,
    BindingResult bindingResult, Model model){
        if(bindingResult.hasErrors()){
            return "signUp";
        }
        userAccountService.saveUser(userForm);
        model.addAttribute("userForm", userForm);
        return "signUp";
    }
}
