package com.Asset.BlackDoorzHotel.DTO.Transaction;

import com.Asset.BlackDoorzHotel.Other.Helper;
import lombok.*;
import net.bytebuddy.asm.Advice;
import org.apache.tomcat.util.net.TLSClientHelloExtractor;

import java.time.LocalDate;
import java.time.temporal.ChronoUnit;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class TransactionDto {

    private Integer Idreserved;
    private String RoomNumber;
    private String Roomtype;
    private String CustUsername;
    private LocalDate Cekin;
    private String cekinformated;
    private Long totalhari;
    private LocalDate Cekout;
    private String cekoutformated;
    private Boolean Status;
    private String Stringstatus;
    private Boolean Expired;
    private String StringExpired;
    private String price;
    private String totalbayar;

    public TransactionDto(Integer idreserved, String roomNumber, String custUsername,
                          LocalDate cekin, LocalDate cekout, Boolean status, Boolean expired){
        this.Idreserved=idreserved;
        this.RoomNumber=roomNumber;
        this.CustUsername=custUsername;
        this.Cekin=cekin;
        this.cekinformated=Helper.konverttanggal(cekin);
        this.Cekout=cekout;
        this.cekoutformated=Helper.konverttanggal(cekout);
        this.Status=status;
        this.Expired = expired;
        var hari = ChronoUnit.DAYS.between(cekin,cekout);
        this.totalhari=hari;
        this.price= Helper.convertuang( (double) hari*200000 );
    }

    public TransactionDto(Integer idreserved, String roomNumber, String custUsername,
                          LocalDate cekin, LocalDate cekout, Boolean status, Boolean expired, Long Price){
        this.Idreserved=idreserved;
        this.RoomNumber=roomNumber;
        this.CustUsername=custUsername;
        this.Cekin=cekin;
        this.cekinformated=Helper.konverttanggal(cekin);
        this.Cekout=cekout;
        this.cekoutformated=Helper.konverttanggal(cekout);
        this.Status=status;
        this.Expired = expired;
        var hari = ChronoUnit.DAYS.between(cekin,cekout);
        this.totalhari=hari;
        this.price= Helper.convertuang( (double) hari*Price);
    }

    public TransactionDto(Integer idreserved, String roomNumber, String roomtype,
                          String custname, LocalDate cekin, LocalDate cekout,
                          Long harga, Boolean status, Boolean expired){
        this.Idreserved=idreserved;
        this.RoomNumber=roomNumber;
        this.Roomtype=roomtype;
        this.CustUsername=custname;
        this.Cekin=cekin;
        this.Cekout=cekout;
        this.cekinformated=Helper.konverttanggal(cekin);
        this.cekoutformated=Helper.konverttanggal(cekout);
        if(status){
            this.Stringstatus="Confirmed";
        } else {
            if(cekin.isBefore(LocalDate.now())){
                this.Stringstatus="Expired";
            } else {
                this.Stringstatus="Pending";
            }
        }
        this.Expired = expired;
        var hari = ChronoUnit.DAYS.between(cekin, cekout);
        if(hari < 1){
            hari = 1;
        }

        this.totalhari=hari;
        this.price=Helper.convertuang((double) harga);
        this.totalbayar=Helper.convertuang((double) hari* harga);
    }

    //untuk confirm
    public TransactionDto(Integer idreserved, String roomNumber, String roomtype,
                          LocalDate cekin, LocalDate cekout, Long price, Boolean status){
        this.Idreserved = idreserved;
        this.RoomNumber = roomNumber;
        this.Roomtype = roomtype;
        this.cekinformated = Helper.konverttanggal(cekin);
        this.cekoutformated = Helper.konverttanggal(cekout);
        this.price = Helper.convertuang((double) price);

        if(ChronoUnit.DAYS.between(cekin, cekout) < 1){
            this.totalbayar = Helper.convertuang((double) price);
        } else {
            this.totalbayar = Helper.convertuang((double) ChronoUnit.DAYS.between(cekin, cekout)*price);
        }
        this.Status = status;
    }
}
