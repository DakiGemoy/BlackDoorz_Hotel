package com.Asset.BlackDoorzHotel.Validation;

import com.Asset.BlackDoorzHotel.DAO.custRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CekCustomerTerdaftarValidator implements ConstraintValidator<cekCustomerTerdaftar, String> {

    @Autowired
    private custRepository custRepos;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(custRepos.cekcustomerterdaftar(s) >= 1){
            return false;
        } else {
            return true;
        }
    }
}
