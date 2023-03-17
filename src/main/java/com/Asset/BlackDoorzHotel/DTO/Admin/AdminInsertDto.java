package com.Asset.BlackDoorzHotel.DTO.Admin;

import com.Asset.BlackDoorzHotel.Validation.checkAdminTerdaftar;
import com.Asset.BlackDoorzHotel.Validation.confirmPassword;
import lombok.*;

import javax.validation.constraints.NotBlank;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@confirmPassword(password = "Password", repassword = "rePassword", message = "Password tidak sama")
public class AdminInsertDto {

    @NotBlank(message = "Tidak boleh kosong")
    @checkAdminTerdaftar(message = "Username sudah terdaftar")
    private String AdminNumber;

    @NotBlank(message = "Tidak boleh kosong")
    private String Password;

    @NotBlank(message = "Tidak boleh kosong")
    private String rePassword;

    @NotBlank(message = "Tidak boleh kosong")
    private String jobtitle;

}
