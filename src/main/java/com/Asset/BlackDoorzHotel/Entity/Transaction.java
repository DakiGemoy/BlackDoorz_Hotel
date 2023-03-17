package com.Asset.BlackDoorzHotel.Entity;

import lombok.*;
import net.bytebuddy.asm.Advice;

import javax.persistence.*;
import java.time.LocalDate;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Entity
@Table(name = "TransReserve")
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "IdReserve")
    private Integer Idreserved;

    @Column(name = "RoomNumber")
    private String RoomNumber;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "RoomNumber", insertable = false, updatable = false)
    private Room room;

    @Column(name = "CustUsername")
    private String CustUsername;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "CustUsername", insertable = false, updatable = false)
    private Customer customer;

    @Column(name = "CheckIn")
    private LocalDate Cekin;

    @Column(name = "CheckOut")
    private LocalDate Cekout;

    @Column(name = "statusreserve")
    private Boolean Status;

    @Column(name = "isExpired")
    private Boolean IsExpired;

    public Transaction(Integer idreserved, String roomNumber, String custUsername,
                       LocalDate cekin, LocalDate cekout, Boolean status, Boolean isExpired){
        this.Idreserved = idreserved;
        this.RoomNumber = roomNumber;
        this.CustUsername = custUsername;
        this.Cekin = cekin;
        this.Cekout = cekout;
        this.Status = status;
        this.IsExpired = isExpired;
    }
}
