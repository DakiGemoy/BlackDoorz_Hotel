package com.Asset.BlackDoorzHotel.Controller;

import com.Asset.BlackDoorzHotel.DTO.Customer.CustomerUpdateDto;
import com.Asset.BlackDoorzHotel.Other.Helper;
import com.Asset.BlackDoorzHotel.Service.Akses.aksesService;
import com.Asset.BlackDoorzHotel.Service.Customer.customerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/customer")
public class CustomerController {

    @Autowired
    private customerService customerServ;

    @Autowired
    private aksesService aksesServ;

    @GetMapping("/list")
    public String viewcust(Model model,
                           @RequestParam(defaultValue = "") String custname,
                           @RequestParam(defaultValue = "1") Integer currentpage){
        model.addAttribute("role", Helper.getrole());
        if (aksesServ.izin(Helper.getrole(), "CustomerList")) {
            model.addAttribute("listcust",customerServ.getlistcust(custname, currentpage));
            model.addAttribute("custsearch",custname);
            model.addAttribute("currentpage", currentpage);
            model.addAttribute("totalpage", customerServ.totalhalaman(custname));
            return "Customer/ListCustomer";
        } else {
            model.addAttribute("status", "Akses ditolak untuk");
            model.addAttribute("tipe", Helper.getrole());
            model.addAttribute("messageError", "Anda tidak memiliki akses untuk melihat laman ini");
            model.addAttribute("Link", "/home/view");
            return "Notification";
        }
    }

    @GetMapping("/delete")
    public String deletecust(@RequestParam String custnumb,
                             Model model){
        model.addAttribute("role", Helper.getrole());
        if (aksesServ.izin(Helper.getrole(), "CustomerDelete")) {
            try {
                if(customerServ.cekdelete(custnumb)){
                    model.addAttribute("status", "Berhasil menghapus");
                    model.addAttribute("tipe", "Customer");
                    model.addAttribute("Link", "/customer/list");
                    return "Notification";
                } else {
                    model.addAttribute("status", "Gagal menghapus");
                    model.addAttribute("tipe", "Customer");
                    model.addAttribute("messageError", "Customer mungkin sudah memiliki data yang terhubung dengan data lainnya");
                    model.addAttribute("Link", "/customer/list");
                    return "Notification";
                }
            } catch (Exception exception){
                model.addAttribute("status", "Gagal menghapus");
                model.addAttribute("tipe", "Customer");
                model.addAttribute("messageError", "Unknown Error");
                model.addAttribute("Link", "/customer/list");
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

    @GetMapping("/transaction")
    public String transcust(@RequestParam String custnumb,
//                            @RequestParam(defaultValue = "1") Integer currentpage,
                            Model model){
        model.addAttribute("role", Helper.getrole());
        if (aksesServ.izin(Helper.getrole(), "CustomerTransaction")) {
            var nama = customerServ.getcustomername(custnumb);
    //        model.addAttribute("currentpage", currentpage);
    //        model.addAttribute("totalpage", customerServ.totalhalamantransaksi(custnumb));
            model.addAttribute("custname", nama);
            model.addAttribute("listrans", customerServ.getlisttransofcust(custnumb));
            return "Transaction/ListTransaction";
        } else {
            model.addAttribute("status", "Akses ditolak untuk");
            model.addAttribute("tipe", Helper.getrole());
            model.addAttribute("messageError", "Anda tidak memiliki akses untuk melihat laman ini");
            model.addAttribute("Link", "/home/view");
            return "Notification";
        }
    }

    @PostMapping("/update")
    public String updateprofile(@Valid @ModelAttribute("dto")CustomerUpdateDto dto,
                                BindingResult binidng, Model model){
        model.addAttribute("role", Helper.getrole());
        if (aksesServ.izin(Helper.getrole(), "CustomerUpdate")) {
            try{
                if(binidng.hasErrors()){
                    model.addAttribute("dto", dto);
                    return "Customer/UpdateProfile";
                } else {
                    customerServ.postupdate(dto);
                    model.addAttribute("status", "Berhasil");
                    model.addAttribute("tipe","update Profile");
                    model.addAttribute("Link", "/home/view");
                    return "Notification";
                }
            } catch (Exception exception){
                model.addAttribute("status", "Gagal");
                model.addAttribute("tipe", "update Profile");
                model.addAttribute("messageError", "Unknwon Error");
                model.addAttribute("Link", "/updateaccount/changepassword");
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
