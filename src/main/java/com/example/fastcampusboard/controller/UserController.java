package com.example.fastcampusboard.controller;

import com.example.fastcampusboard.domain.UserAccount;
import com.example.fastcampusboard.dto.UserAccountDto;
import com.example.fastcampusboard.dto.request.UserForm;
import com.example.fastcampusboard.dto.security.BoardPrincipal;
import com.example.fastcampusboard.service.UserAccountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;
import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Controller
@RequiredArgsConstructor
@Slf4j
public class UserController {
    private final UserAccountService userAccountService;

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
            userForm.getPassword(),
            userForm.getEmail(),
            userForm.getNickname(),
            userForm.getMemo()
        );
        return "redirect:/";
    }
}
