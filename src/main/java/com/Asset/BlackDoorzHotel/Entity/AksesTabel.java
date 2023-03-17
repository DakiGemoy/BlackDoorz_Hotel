package com.Asset.BlackDoorzHotel.Entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Getter @Setter
@Entity
@Table(name = "AccessMenu")
public class AksesTabel {

    @Id
    @Column(name = "Posisi")
    private String AksesRole;

    @Column(name = "HomeView")
    private Boolean HomeView;

    @Column(name = "UpdateAccountChangePassword")
    private Boolean UpdateAccountChangePassword;

    /////////////////////////////////////////

    @Column(name = "RoomList")
    private Boolean RoomList;

    @Column(name = "RoomUpsert")
    private Boolean RoomUpsert;

    @Column(name = "RoomAdd")
    private Boolean RoomAdd;

    @Column(name = "RoomUpdate")
    private Boolean RoomUpdate;

    @Column(name = "RoomDelete")
    private Boolean RoomDelete;

    @Column(name = "RoomDetail")
    private Boolean RoomDetail;

    //////////////////////////////

    @Column(name = "CustomerList")
    private Boolean CustomerList;

    @Column(name = "CustomerDelete")
    private Boolean CustomerDelete;

    @Column(name = "CustomerTransaction")
    private Boolean CustomerTransaction;

    @Column(name = "CustomerUpdate")
    private Boolean CustomerUpdate;

    ////////////////////////////////////

    @Column(name = "AdminList")
    private Boolean AdminList;

    @Column(name = "AdminInsert")
    private Boolean AdminInsert;

    @Column(name = "AdminAdd")
    private Boolean AdminAdd;

    @Column(name = "AdminDelete")
    private Boolean AdminDelete;

    @Column(name = "AdminUpdate")
    private Boolean AdminUpdate;

    ///////////////////////////////////

    @Column(name = "ReservedCurrentReserved")
    private Boolean ReservedCurrentReserved;

    @Column(name = "ReservedDelete")
    private Boolean ReservedDelete;

    @Column(name = "ReservedReservation")
    private Boolean ReservedReservation;

    @Column(name = "ReservedBill")
    private Boolean ReservedBill;

    @Column(name = "ReservedConfirm")
    private Boolean ReservedConfirm;

    @Column(name = "ReservMyReserv")
    private Boolean ReservMyReserv;
}
