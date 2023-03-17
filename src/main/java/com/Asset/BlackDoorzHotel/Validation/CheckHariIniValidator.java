package com.Asset.BlackDoorzHotel.Validation;

import net.bytebuddy.asm.Advice;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class CheckHariIniValidator implements ConstraintValidator<checkHariIni, LocalDate> {
    @Override
    public boolean isValid(LocalDate localDate, ConstraintValidatorContext constraintValidatorContext) {
        if(localDate == null){
            return false;
        } else {
            if(localDate.isBefore(LocalDate.now())){
                return false;
            } else {
                return true;
            }
        }
    }
}
