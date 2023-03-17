package com.Asset.BlackDoorzHotel.Entity;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Entity
@Table(name = "Room")
public class Room {

    @Id
    @Column(name = "RoomNumber")
    private String RoomNumber;

    @Column(name = "Type")
    private String Type;

    @Column(name = "Price")
    private Long Price;

    @Column(name = "Reserved")
    private Boolean Reserved;

    @Column(name = "Confirmed")
    private Boolean Confirmed;
}
