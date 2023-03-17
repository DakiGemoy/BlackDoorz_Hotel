package com.Asset.BlackDoorzHotel.DTO.Customer;

import com.Asset.BlackDoorzHotel.Validation.cekCustomerTerdaftar;
import com.Asset.BlackDoorzHotel.Validation.confirmPassword;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@confirmPassword(password = "Password", repassword = "repassword", message = "Password tidak sama")
public class CustomerInsertDto {

    @NotBlank(message = "Tidak boleh kosong")
    @Size(max = 20, message = "Maks 20 karakter")
    @cekCustomerTerdaftar(message = "Username ini telah terdaftar")
    private String Username;

    @NotBlank(message = "Tidak boleh kosong")
    @Size(max = 20, message = "Maksimal 20 karakter")
    private String Password;

    @NotBlank(message = "Tidak boleh kosong")
    @Size(max = 20, message = "Maksimal 20 karakter")
    private String repassword;

    @NotBlank(message = "Tidak boleh kosong")
    @Size(max = 30, message = "Maksimal 30 karakter")
    private String Name;

    @NotBlank(message = "Tidak boleh kosong")
    @Size(max = 50, message = "Maksimal 50 karakter")
    private String Email;

    @Size(max = 200, message = "Maksimal 200 karakter")
    private String Address;
}
