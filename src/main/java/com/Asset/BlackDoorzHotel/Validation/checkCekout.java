package com.Asset.BlackDoorzHotel.Validation;

import javax.validation.Constraint;
import javax.validation.Payload;
import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.time.LocalDate;

@Constraint(validatedBy = CheckCekoutValidator.class)
@Target({ElementType.TYPE})
@Retention(RetentionPolicy.RUNTIME)
public @interface checkCekout {
    public Class<?>[] groups() default {};
    public Class<? extends Payload>[] payload() default {};
    public String message();

    public String CheckIn();
    public String CheckOut();
}
