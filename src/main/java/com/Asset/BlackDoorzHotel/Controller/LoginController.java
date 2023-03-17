package com.Asset.BlackDoorzHotel.Controller;

import com.Asset.BlackDoorzHotel.DTO.Customer.CustomerInsertDto;
import com.Asset.BlackDoorzHotel.Service.Customer.customerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.validation.Valid;

@Controller
@RequestMapping("/login")
public class LoginController {

    @Autowired
    private customerService customerServ;

    @GetMapping("/formlogin")
    public String loginview(){
        return "Login/loginForm";
    }

    @GetMapping("/regist")
    public String regist(Model model){
        CustomerInsertDto baru = new CustomerInsertDto();
        model.addAttribute("dto", baru);
        return "Login/registForm";
    }

    @PostMapping("/regist")
    public String postregist(@Valid @ModelAttribute("dto") CustomerInsertDto dto,
                             BindingResult bindingResult, Model model){
        try {
            if (bindingResult.hasErrors()){
                model.addAttribute("dto", dto);
                return "Login/registForm";
            } else {
                customerServ.postregist(dto);
                return "Login/loginForm";
            }
        } catch (Exception exception){
            return "";
        }
    }
}
