package com.Asset.BlackDoorzHotel.DTO.Admin;

import com.Asset.BlackDoorzHotel.Validation.confirmPassword;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.Size;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@confirmPassword(password = "Password", repassword = "repassword", message = "Password tidak cocok")
public class AdminUpdateDto {

    private String AdminNumber;

    @NotBlank(message = "Tidak boleh kosong")
    @Size(max = 20, message = "Maksimal 20 karakter")
    private String Password;

    @NotBlank(message = "Tidak boleh kosong")
    @Size(max = 20, message = "Maksimal 20 karakter")
    private String repassword;

    @NotBlank(message = "Tidak boleh kosong")
    @Size(max = 20, message = "Maksimal 20 karakter")
    private String jobtitle;
}
