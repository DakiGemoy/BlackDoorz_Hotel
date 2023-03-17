package com.Asset.BlackDoorzHotel.Entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Entity
@Table(name = "Customer")
public class Customer {

    @Id
    @Column(name = "Username")
    private String Username;

    @Column(name = "Password")
    private String Password;

    @Column(name = "Name")
    private String Name;

    @Column(name = "Email")
    private String Email;

    @Column(name = "Address")
    private String Address;
}
