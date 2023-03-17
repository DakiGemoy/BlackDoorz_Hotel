package com.Asset.BlackDoorzHotel.Controller;

import com.Asset.BlackDoorzHotel.DTO.Room.RoomInsertDto;
import com.Asset.BlackDoorzHotel.DTO.Room.RoomUpdateDto;
import com.Asset.BlackDoorzHotel.Other.Helper;
import com.Asset.BlackDoorzHotel.Service.Akses.aksesService;
import com.Asset.BlackDoorzHotel.Service.Room.roomService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/room")
public class RoomController {

    @Autowired
    private roomService roomServ;

    @Autowired
    private aksesService aksesServ;

    @GetMapping("/list")
    public String listroom(Model model,
                           @RequestParam(defaultValue = "") String roomnumb,
                           @RequestParam(defaultValue = "1") Integer currentpage) {
        model.addAttribute("role", Helper.getrole());
        if (aksesServ.izin(Helper.getrole(), "RoomList")) {
            model.addAttribute("listroom",roomServ.getlistroom(roomnumb, currentpage));
            model.addAttribute("totalpage", roomServ.totaldatahalaman(roomnumb));
            model.addAttribute("currentpage", currentpage);
            model.addAttribute("roomnumb",roomnumb);
            return "Room/ListRoom";
        } else {
            model.addAttribute("status", "Akses ditolak untuk");
            model.addAttribute("tipe", Helper.getrole());
            model.addAttribute("messageError", "Anda tidak memiliki akses untuk melihat laman ini");
            model.addAttribute("Link", "/home/view");
            return "Notification";
        }
    }

    @GetMapping("/upsert")
    public String viewadd(Model model,
                          @RequestParam(required = false) String roomnumb){
        model.addAttribute("role", Helper.getrole());
        if (aksesServ.izin(Helper.getrole(), "Roomupsert")) {
            if(roomnumb == null){
                RoomInsertDto baru = new RoomInsertDto();
                model.addAttribute("dto", baru);
                return "Room/InsertRoom";
            } else {
                if(roomServ.cekpaksaupdateroom(roomnumb)){
                    model.addAttribute("dto", roomServ.getupdate(roomnumb));
                    return "Room/UpdateRoom";
                } else {
                    model.addAttribute("status", "Gagal request update");
                    model.addAttribute("tipe","Room "+roomnumb);
                    model.addAttribute("messageError","Dikarenakan room ini sedang digunakan client / sudah di book client");
                    model.addAttribute("Link","/room/list");
                    return "Notification";
                }
            }
        } else {
            model.addAttribute("status", "Akses ditolak untuk");
            model.addAttribute("tipe", Helper.getrole());
            model.addAttribute("messageError", "Anda tidak memiliki akses untuk melihat laman ini");
            model.addAttribute("Link", "/home/view");
            return "Notification";
        }
    }

    @PostMapping("/add")
    public String postinsertroom(@Valid @ModelAttribute("dto") RoomInsertDto dto,
                                 BindingResult binding, Model model){
        model.addAttribute("role", Helper.getrole());
        if (aksesServ.izin(Helper.getrole(), "RoomAdd")) {
            try {
                if(binding.hasErrors()){
                    model.addAttribute("dto", dto);
                    return "Room/InsertRoom";
                } else {
                    roomServ.insertroom(dto);
                    model.addAttribute("status", "Berhasil Menambahkan");
                    model.addAttribute("tipe","Room "+dto.getRoomNumber());
                    model.addAttribute("Link","/room/list");
                    return "Notification";
                }
            } catch (Exception exception){
                model.addAttribute("status", "Gagal menambahkan");
                model.addAttribute("tipe","Room");
                model.addAttribute("messageError","Unknown Error");
                model.addAttribute("Link","/room/upsert");
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
    public String postnewupdate(@Valid @ModelAttribute("dto") RoomUpdateDto dto,
                                BindingResult bindingResult, Model model){
        model.addAttribute("role", Helper.getrole());
        if (aksesServ.izin(Helper.getrole(), "RoomUpdate")) {
            try {
                if(bindingResult.hasErrors()){
                    model.addAttribute("dto", dto);
                    return "Room/UpdateRoom";
                } else {
                    roomServ.updateroom(dto);
                    model.addAttribute("status", "Berhasil Update");
                    model.addAttribute("tipe","Room "+dto.getRoomNumber());
                    model.addAttribute("Link","/room/list");
                    return "Notification";
                }
            } catch (Exception exception){
                model.addAttribute("status", "Gagal menambahkan");
                model.addAttribute("tipe","Room");
                model.addAttribute("messageError","Unknown Error");
                model.addAttribute("Link","/room/upsert?roomnumb="+dto.getRoomNumber());
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

    @GetMapping("/delete")
    public String deleteroom(@RequestParam String roomnumb,
                             Model model){
        model.addAttribute("role", Helper.getrole());
        if (aksesServ.izin(Helper.getrole(), "RoomDelete")) {
            if(roomServ.cekterhubung(roomnumb)){
                model.addAttribute("status", "Berhasil menghapus");
                model.addAttribute("tipe","Room "+roomnumb);
                model.addAttribute("Link","/room/list");
                return "Notification";
            } else {
                model.addAttribute("status", "Gagal menghapus");
                model.addAttribute("tipe","Room "+roomnumb);
                model.addAttribute("messageError","Room ini ada history transaksi yang sudah atau sedang berlangsung");
                model.addAttribute("Link","/room/list");
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


    @GetMapping("/detail")
    public String detailroom(@RequestParam String roomnumb,
                             Model model){
        model.addAttribute("role", Helper.getrole());
        if (aksesServ.izin(Helper.getrole(), "RoomDetail")) {
            try {
                model.addAttribute("obj", roomServ.gettransactionbyroomnumber(roomnumb));
                return "Room/detailRoom";
            } catch (Exception exception){
                return "";
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
