package com.Asset.BlackDoorzHotel.Controller;

import com.Asset.BlackDoorzHotel.AnotherConfigturation.AppUserDetails;
import com.Asset.BlackDoorzHotel.Other.Helper;
import com.Asset.BlackDoorzHotel.Service.Admin.adminService;
import com.Asset.BlackDoorzHotel.Service.Akses.aksesService;
import com.Asset.BlackDoorzHotel.Service.Customer.customerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/updateaccount")
public class ChangePasswordController {

    @Autowired
    private adminService adminServ;

    @Autowired
    private customerService customerServ;

    @Autowired
    private aksesService aksesServ;

    @GetMapping("/changepassword")
    public String ubahpassword(Model model){
        model.addAttribute("role", Helper.getrole());
        if (aksesServ.izin(Helper.getrole(), "UpdateAccountChangePassword")) {
            if(Helper.getrole().equals("Customer")){
                model.addAttribute("dto", customerServ.getcustbycustname(Helper.getuser().getUsername()));
                return "Customer/UpdateProfile";
            } else {
                model.addAttribute("dto", adminServ.getupdate(Helper.getuser().getUsername()));
                return "Admin/changePasswordAdmin";
            }
        } else {
            model.addAttribute("status", "Akses ditolak untuk");
            model.addAttribute("tipe", Helper.getrole());
            model.addAttribute("messageError", "Anda tidak memiliki akses untuk melihat laman ini");
            model.addAttribute("Link", "/home/view");
            return "Notification";
        }
    }

}
