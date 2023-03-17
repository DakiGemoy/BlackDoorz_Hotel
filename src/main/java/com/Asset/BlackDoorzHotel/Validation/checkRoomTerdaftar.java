package com.Asset.BlackDoorzHotel.Validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Constraint(validatedBy = CheckRoomTerdaftarValidator.class)
@Target({ElementType.FIELD})
@Retention(RetentionPolicy.RUNTIME)
public @interface checkRoomTerdaftar {
    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default{};
    public String message();
}
