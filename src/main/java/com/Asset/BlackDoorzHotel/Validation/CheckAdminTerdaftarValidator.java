package com.Asset.BlackDoorzHotel.Validation;

import com.Asset.BlackDoorzHotel.DAO.adminRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckAdminTerdaftarValidator implements ConstraintValidator<checkAdminTerdaftar, String> {

    @Autowired
    private adminRepository adminRepos;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(adminRepos.cekadminterdaftar(s)>0){
            return false;
        } else {
            return true;
        }
    }
}
