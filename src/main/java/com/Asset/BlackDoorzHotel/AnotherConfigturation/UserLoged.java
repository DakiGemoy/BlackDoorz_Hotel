package com.Asset.BlackDoorzHotel.AnotherConfigturation;

import lombok.*;

@AllArgsConstructor @NoArgsConstructor
@Getter @Setter
public class UserLoged {
    private String username;
    private String password;
    private String role;
    private String messageError;
}
