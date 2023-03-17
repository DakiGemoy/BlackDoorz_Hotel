package com.Asset.BlackDoorzHotel.Validation;

import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class ConfirmPasswordValidator implements ConstraintValidator<confirmPassword, Object> {

    private String password;
    private String repassword;

    @Override
    public void initialize(confirmPassword constraintAnnotation) {
        this.password=constraintAnnotation.password();
        this.repassword=constraintAnnotation.repassword();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        String newpas = new BeanWrapperImpl(o).getPropertyValue(password).toString();
        String newrepas = new BeanWrapperImpl(o).getPropertyValue(repassword).toString();
        if(newpas.equals(newrepas)){
            return true;
        } else {
            return false;
        }
    }
}
