package com.example.fastcampusboard.controller;

import com.example.fastcampusboard.dto.request.UserForm;
import com.example.fastcampusboard.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserAccountService userAccountService;
    private final PasswordEncoder passwordEncoder;

    @GetMapping("/login")
    public String login(){
        return "login";
    }
    @GetMapping("/signUp")
    public String signUp(@ModelAttribute("userForm") UserForm userForm){
            return "signUp";
        }

    @PostMapping("/signUp")
    public String signUpRequest(@Valid @ModelAttribute UserForm userForm, BindingResult bindingResult, Model model){

        if(bindingResult.hasErrors()){
            /* 유효성 검사를 통과하지 못 한 필드와 메시지 핸들링 */
            Map<String, String> errorMap = new HashMap<>();

            for(FieldError error : bindingResult.getFieldErrors()) {
                errorMap.put("valid_"+error.getField(), error.getDefaultMessage());
                log.info("error message : "+error.getDefaultMessage());
            }
            model.addAttribute("msg", bindingResult.getFieldErrors().toString());
            /* 회원가입 페이지로 리턴 */
            return "/signUp";
        }

        userAccountService.saveUser(
            userForm.getUserId(),
            passwordEncoder.encode(userForm.getPassword()),
            userForm.getEmail(),
            userForm.getNickname(),
            userForm.getMemo()
        );
        return "redirect:/";
    }
    @GetMapping("/logout")
    public String logout(HttpServletRequest request, HttpServletResponse response) {
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        if (auth != null){
            new SecurityContextLogoutHandler().logout(request, response, auth);
        }
        return "redirect:/";
    }
}
