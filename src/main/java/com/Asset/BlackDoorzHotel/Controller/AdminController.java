package com.Asset.BlackDoorzHotel.Controller;

import com.Asset.BlackDoorzHotel.DTO.Admin.AdminInsertDto;
import com.Asset.BlackDoorzHotel.DTO.Admin.AdminUpdateDto;
import com.Asset.BlackDoorzHotel.Other.Helper;
import com.Asset.BlackDoorzHotel.Service.Admin.adminService;
import com.Asset.BlackDoorzHotel.Service.Akses.aksesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/admin")
public class AdminController {

    @Autowired
    private adminService adminServ;

    @Autowired
    private aksesService aksesServ;

    @GetMapping("/list")
    public String viewlistadmin(Model model){
        model.addAttribute("role", Helper.getrole());
        if (aksesServ.izin(Helper.getrole(), "AdminList")) {
            model.addAttribute("listadmin", adminServ.getlistadmin());
            return "Admin/ListAdmin";
        } else {
            model.addAttribute("status", "Akses ditolak untuk");
            model.addAttribute("tipe", Helper.getrole());
            model.addAttribute("messageError", "Anda tidak memiliki akses untuk melihat laman ini");
            model.addAttribute("Link", "/home/view");
            return "Notification";
        }
    }

    @GetMapping("/insert")
    public String viewupsertadmin(Model model){
        model.addAttribute("role", Helper.getrole());
        if (aksesServ.izin(Helper.getrole(), "AdminInsert")) {
            AdminInsertDto baru = new AdminInsertDto();
            model.addAttribute("dto", baru);
            return "Admin/insertAdmin";
        } else {
            model.addAttribute("status", "Akses ditolak untuk");
            model.addAttribute("tipe", Helper.getrole());
            model.addAttribute("messageError", "Anda tidak memiliki akses untuk melihat laman ini");
            model.addAttribute("Link", "/home/view");
            return "Notification";
        }
    }

    @PostMapping("/add")
    public String postinsertadmin(@Valid @ModelAttribute("dto") AdminInsertDto dto,
                                  BindingResult binding, Model model){
        model.addAttribute("role", Helper.getrole());
        if (aksesServ.izin(Helper.getrole(), "AdminAdd")) {
            if(binding.hasErrors()){
                model.addAttribute("dto", dto);
                return "Admin/insertAdmin";
            } else {
                adminServ.postinsertadmin(dto);
                return "redirect:/admin/list";
            }
        } else {
            model.addAttribute("status", "Akses ditolak untuk");
            model.addAttribute("tipe", Helper.getrole());
            model.addAttribute("messageError", "Anda tidak memiliki akses untuk melihat laman ini");
            model.addAttribute("Link", "/home/view");
            return "Notification";
        }
    }

    @GetMapping("/delete")
    public String deleteadmin(@RequestParam String admnumb,
                              Model model){
        model.addAttribute("role", Helper.getrole());
        if (aksesServ.izin(Helper.getrole(), "AdminDelete")) {
            try{
                adminServ.deleteadmin(admnumb);
                model.addAttribute("status", "Berhasil menhapus");
                model.addAttribute("tipe", "Admin");
                model.addAttribute("Link","/admin/list");
                return "Notification";
            } catch (Exception exception){
                model.addAttribute("status","Gagal menghapus");
                model.addAttribute("tipe", "Admin");
                model.addAttribute("messageError", "Unknown Error");
                model.addAttribute("Link","/admin/list");
                return "Notification";
            }
        } else {
            model.addAttribute("status", "Akses ditolak untuk");
            model.addAttribute("tipe", Helper.getrole());
            model.addAttribute("messageError", "Anda tidak memiliki akses untuk melihat laman ini");
            model.addAttribute("Link", "/home/view");
            return "Notification";
        }
    }

    @PostMapping("/update")
    public String changepassword(@Valid @ModelAttribute("dto")AdminUpdateDto dto,
                                 BindingResult binding, Model model){
        model.addAttribute("role", Helper.getrole());
        if (aksesServ.izin(Helper.getrole(), "AdminUpdate")) {
            try {
                if(binding.hasErrors()){
                    model.addAttribute("dto", dto);
                    return "Admin/changePasswordAdmin";
                } else {
                    adminServ.postupdateadmin(dto);
                    model.addAttribute("status","Berhasil mengubah password");
                    model.addAttribute("tipe","Admin");
                    model.addAttribute("Link","/home/view");
                    return "Notification";
                }
            } catch (Exception exception){
                model.addAttribute("status","Gagal mengubah password");
                model.addAttribute("tipe","Admin");
                model.addAttribute("messageError", "Unknown Error");
                model.addAttribute("Link","/updateaccount/changepassword");
                return "Notification";
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
