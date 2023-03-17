package com.Asset.BlackDoorzHotel.DTO.Room;

import com.Asset.BlackDoorzHotel.Other.Helper;
import lombok.*;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class RoomDto {

    private String RoomNumber;
    private String Type;
    private Long Price;
    private Boolean Reserved;
    private Boolean Confirmed;
    private String status;
    private String hargaformated;

    public RoomDto(String roomNumber, String type,
                   Long price, Boolean reserved, Boolean confirmed){
        this.RoomNumber=roomNumber;
        this.Type=type;
        this.Price=price;
        this.Reserved=reserved;
        this.Confirmed=confirmed;
        this.hargaformated= Helper.convertuang((double) price);
        if(reserved && confirmed){
            this.status="Occupied";
        } else if(reserved && !confirmed){
            this.status="Reserved";
        } else if(!reserved && confirmed){
            this.status="Something Error";
        } else {
            this.status="Vacant";
        }
    }

}
