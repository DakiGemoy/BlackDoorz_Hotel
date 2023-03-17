package com.Asset.BlackDoorzHotel.DTO.Customer;

import com.Asset.BlackDoorzHotel.Validation.confirmPassword;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@NoArgsConstructor @AllArgsConstructor
@Getter @Setter
@confirmPassword(password = "Password", repassword = "repassword", message = "Password tidak sama")
public class CustomerUpdateDto {

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
