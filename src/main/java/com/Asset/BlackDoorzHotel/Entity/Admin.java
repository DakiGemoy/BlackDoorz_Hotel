package com.Asset.BlackDoorzHotel.Entity;

import lombok.*;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
@Entity
@Table(name = "Administrator")
public class Admin {

    @Id
    @Column(name = "AdminNumb")
    private String AdminNumber;

    @Column(name = "Password")
    private String Password;

    @Column(name = "Title")
    private String jobtitle;

}
