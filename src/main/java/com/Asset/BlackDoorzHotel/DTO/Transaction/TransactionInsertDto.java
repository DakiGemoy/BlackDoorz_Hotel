package com.Asset.BlackDoorzHotel.DTO.Transaction;

import com.Asset.BlackDoorzHotel.Other.Helper;
import com.Asset.BlackDoorzHotel.Validation.checkCekout;
import com.Asset.BlackDoorzHotel.Validation.checkHariIni;
import lombok.*;
import org.springframework.format.annotation.DateTimeFormat;

import javax.validation.constraints.NotNull;
import java.time.LocalDate;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@checkCekout(CheckIn = "Cekin", CheckOut = "Cekout",
        message = "Check out harus terjadi di hari yang sama dengan check in atau setelah check in terjadi.")
public class TransactionInsertDto {

    private Integer Idreseved;
    private String RoomNumber;
    private String Roomtype;
    private String CustUsername;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Tidak boleh kosong")
    @checkHariIni(message = "Tidak boleh kurang dari hari ini")
    private LocalDate Cekin;

    private String cekinversistring;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @NotNull(message = "Tidak boleh kosong")
    private LocalDate Cekout;

    private String cekoutversistring;

    private Long priceday;
    private String pricedayformated;

    private Boolean status;

    public TransactionInsertDto (String roomNumber, String roomtype,
                                 String custUsername, LocalDate cekin,
                                 LocalDate cekout, Long priceday){
        this.RoomNumber = roomNumber;
        this.Roomtype = roomtype;
        this.CustUsername = custUsername;
        this.Cekin = cekin;
        this.Cekout = cekout;
        this.priceday = priceday;
        this.pricedayformated = Helper.convertuang(Double.valueOf(priceday));
        this.cekinversistring = cekin.toString();
        this.cekoutversistring = cekout.toString();
    }

}
