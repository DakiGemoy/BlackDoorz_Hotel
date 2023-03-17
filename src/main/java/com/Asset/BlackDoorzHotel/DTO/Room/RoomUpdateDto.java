package com.Asset.BlackDoorzHotel.DTO.Room;

import com.Asset.BlackDoorzHotel.Validation.checkRoomTerdaftar;
import lombok.*;

import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class RoomUpdateDto {

    private String RoomNumber;

    @NotBlank(message = "Tidak boleh kosong")
    @Size(max = 20, message = "Maksimal 20 karakter")
    private String Type;

    @NotNull(message = "Tidak boleh kosong")
    private Long Price;

    private Boolean Reserved;

    private Boolean Confirmed;
}
