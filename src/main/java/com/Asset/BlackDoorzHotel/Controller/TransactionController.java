package com.Asset.BlackDoorzHotel.Controller;

import com.Asset.BlackDoorzHotel.DTO.Transaction.TransactionDto;
import com.Asset.BlackDoorzHotel.DTO.Transaction.TransactionInsertDto;
import com.Asset.BlackDoorzHotel.Other.Helper;
import com.Asset.BlackDoorzHotel.Service.Akses.aksesService;
import com.Asset.BlackDoorzHotel.Service.Transaction.transactionService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Controller
@RequestMapping("/reserved")
public class TransactionController {

    @Autowired
    private transactionService transactionServ;

    @Autowired
    private aksesService aksesServ;

    @GetMapping("/currentreserved")
    public String listallreserv(Model model,
                                @RequestParam(defaultValue = "") String custname,
                                @RequestParam(defaultValue = "") String roomnumb){
        model.addAttribute("role", Helper.getrole());
        if (aksesServ.izin(Helper.getrole(), "ReservedCurrentReserved")) {
            try{
                var list =  transactionServ.getalltransaction(custname, roomnumb);
                model.addAttribute("lisresv", list);
                model.addAttribute("custname", custname);
                model.addAttribute("roomnumb", roomnumb);
                return "Transaction/ListReservation";
            } catch (Exception exception){
                model.addAttribute("status","Gagal requesting, error pada");
                model.addAttribute("tipe", "Server");
                model.addAttribute("messageError", "Unknown Error");
                model.addAttribute("Link","/home/view");
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
    public String hapusreserved(@RequestParam Integer idreserv,
                                Model model){
        model.addAttribute("role", Helper.getrole());
        if (aksesServ.izin(Helper.getrole(), "ReservedDelete")){
//            try {
                if(transactionServ.deletedtransaction(idreserv)){
                    model.addAttribute("status","Berhasil menghapus");
                    model.addAttribute("tipe","Transaksi");
                    model.addAttribute("Link", "/reserved/currentreserved");
                } else {
                    model.addAttribute("status","Gagal menghapus");
                    model.addAttribute("tipe","Transaksi");
                    model.addAttribute("messageError", "Kesalahan saat penghapusan data");
                    model.addAttribute("Link", "/reserved/currentreserved");
                }
//            } catch (Exception exception){
    //
//            }
            return "Notification";
        } else {
            model.addAttribute("status", "Akses ditolak untuk");
            model.addAttribute("tipe", Helper.getrole());
            model.addAttribute("messageError", "Anda tidak memiliki akses untuk melihat laman ini");
            model.addAttribute("Link", "/home/view");
            return "Notification";
        }
    }

    @GetMapping("/reservation")
    public String listmakereservation(Model model,
                                      @RequestParam(defaultValue = "") String type){
        model.addAttribute("role", Helper.getrole());
        if (aksesServ.izin(Helper.getrole(), "ReservedReservation")){
            model.addAttribute("listrom", transactionServ.getallfromroom(type));
            return "Transaction/MakeReservation";
        } else {
            model.addAttribute("status", "Akses ditolak untuk");
            model.addAttribute("tipe", Helper.getrole());
            model.addAttribute("messageError", "Anda tidak memiliki akses untuk melihat laman ini");
            model.addAttribute("Link", "/home/view");
            return "Notification";
        }
    }

    @GetMapping("/bill")
    public String pesan(Model model,
                        @RequestParam String roomnumber) {
        model.addAttribute("role", Helper.getrole());
        if (aksesServ.izin(Helper.getrole(), "ReservedBill")){
            TransactionInsertDto baru = new TransactionInsertDto();
            var dataroom = transactionServ.getroomforreserve(roomnumber);

            baru.setCustUsername(Helper.getuser().getUsername());
            baru.setRoomtype(dataroom.getType());
            baru.setRoomNumber(dataroom.getRoomNumber());
            baru.setPriceday(dataroom.getPrice());
            baru.setPricedayformated(dataroom.getHargaformated());

            model.addAttribute("dto", baru);
            return "Transaction/InputReserv";
        } else {
            model.addAttribute("status", "Akses ditolak untuk");
            model.addAttribute("tipe", Helper.getrole());
            model.addAttribute("messageError", "Anda tidak memiliki akses untuk melihat laman ini");
            model.addAttribute("Link", "/home/view");
            return "Notification";
        }
    }

    @PostMapping("/bill")
    public String postpesan(@Valid @ModelAttribute("dto") TransactionInsertDto dto,
                            BindingResult binding, Model model){
        model.addAttribute("role", Helper.getrole());
        if (aksesServ.izin(Helper.getrole(), "ReservedBill")){
            try {
                if(binding.hasErrors()){
                    dto.setPricedayformated(Helper.convertuang((double) dto.getPriceday()));
                    model.addAttribute("dto", dto);
                    return "Transaction/InputReserv";
                } else {
                    transactionServ.postreserved(dto);
                    model.addAttribute("status", "Berhasil");
                    model.addAttribute("tipe", "Reservasi");
                    model.addAttribute("Link", "/home/view");
                    return "Notification";
                }
            } catch (Exception exception){
                model.addAttribute("status", "Gagal");
                model.addAttribute("tipe", "Reservasi");
                model.addAttribute("messageError", "Kesalahan saat pengolahan data");
                model.addAttribute("Link", "/home/view");
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

    @GetMapping("/confirm")
    public String getconfirmreserv(Model model){
        model.addAttribute("role", Helper.getrole());
        if (aksesServ.izin(Helper.getrole(), "ReservedConfirm")){
            var cek =  transactionServ.getconfirm(Helper.getuser().getUsername());
            if(cek != null){
                model.addAttribute("dto",cek);
                return "Transaction/confirmReserv";
            } else {
                model.addAttribute("status", "Anda belum memiliki");
                model.addAttribute("tipe", "reservasi");
                model.addAttribute("messageError", "Silahkan membuat reservasi terlebih dahulu");
                model.addAttribute("Link", "/reserved/reservation");
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

    @PostMapping("/confirm")
    public String postconfirmreserv(@ModelAttribute("dto") TransactionDto dto,
                                    Model model){
        model.addAttribute("role", Helper.getrole());
        if (aksesServ.izin(Helper.getrole(), "ReservedConfirm")){
            try {
                transactionServ.postconfirm(dto.getIdreserved());
                model.addAttribute("status","Berhasil");
                model.addAttribute("tipe","Confirm Reservation");
                model.addAttribute("messageError", "dengan code Reservation ="+dto.getIdreserved());
                model.addAttribute("Link", "/reserved/confirm");
                return "Notification";
            } catch (Exception exception){
                model.addAttribute("status","Gagal");
                model.addAttribute("tipe","Confirm Reservation");
                model.addAttribute("messageError", "Kesalahan saat pengkinian data");
                model.addAttribute("Link", "/reserved/confirm");
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

    @GetMapping("/myreserv")
    public String myreserv(Model model){
        model.addAttribute("role", Helper.getrole());
        if(aksesServ.izin(Helper.getrole(), "ReservedReserv") ){
            var cek = transactionServ.getalltransbyuser(Helper.getuser().getUsername());
            model.addAttribute("listrans", cek);
            return "Transaction/myReserv";
        } else {
            model.addAttribute("status", "Akses ditolak untuk");
            model.addAttribute("tipe", Helper.getrole());
            model.addAttribute("messageError", "Anda tidak memiliki akses untuk melihat laman ini");
            model.addAttribute("Link", "/home/view");
            return "Notification";
        }
    }

}
