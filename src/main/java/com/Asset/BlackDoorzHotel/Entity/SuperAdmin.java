package com.Asset.BlackDoorzHotel.Entity;


import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Entity
@Table(name = "SuperAdmin")
public class SuperAdmin {

    @Id
    @Column(name = "SupAdminNumb")
    private String SuperAdminNumb;

    @Column(name = "Password")
    private String Password;

    @Column(name = "FirstName")
    private String Firstname;

    @Column(name = "LastName")
    private String Lastname;
}
