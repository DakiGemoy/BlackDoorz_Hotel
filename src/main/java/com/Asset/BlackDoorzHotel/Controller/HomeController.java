package com.Asset.BlackDoorzHotel.Controller;

import com.Asset.BlackDoorzHotel.Other.Helper;
import com.Asset.BlackDoorzHotel.Service.Akses.aksesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@RequestMapping("/home")
public class HomeController {

    @Autowired
    private aksesService aksesServ;

    @GetMapping("/view")
    public String homeview(Model model){
        if (aksesServ.izin(Helper.getrole(), "HomeView")) {
            model.addAttribute("role", Helper.getrole());
            return "Home/HomeView";
        } else {
            model.addAttribute("status", "Akses ditolak untuk");
            model.addAttribute("tipe", Helper.getrole());
            model.addAttribute("messageError", "Anda tidak memiliki akses untuk melihat laman ini");
            model.addAttribute("Link", "/home/view");
            return "Notification";
        }
    }
}
