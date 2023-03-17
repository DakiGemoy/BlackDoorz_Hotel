package com.Asset.BlackDoorzHotel.Other;

import com.Asset.BlackDoorzHotel.AnotherConfigturation.AppUserDetails;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.text.NumberFormat;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.Locale;

public class Helper {
    static Locale indo = new Locale("id", "ID");

    public static String convertuang(Double uang){
        return NumberFormat.getCurrencyInstance(indo).format(uang);
    }

    public static String konverttanggal(LocalDate tanggal){
        DateTimeFormatter baru = DateTimeFormatter.ofPattern("dd-MM-yyy", indo);
        return baru.format(tanggal);
    }

    public static String getrole(){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        var asd = (AppUserDetails) auth.getPrincipal();
        var sss = asd.getAuthorities();

        String role = "";
        for(GrantedAuthority a : sss){
            role = a.getAuthority();
        }
        return role;
    }

    public static AppUserDetails getuser(){
        Authentication auth= SecurityContextHolder.getContext().getAuthentication();
        var asd = (AppUserDetails) auth.getPrincipal();

        return asd;
    }
}
