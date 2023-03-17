package com.Asset.BlackDoorzHotel.Validation;

import com.Asset.BlackDoorzHotel.DAO.roomRepository;
import org.springframework.beans.factory.annotation.Autowired;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;

public class CheckRoomTerdaftarValidator implements ConstraintValidator<checkRoomTerdaftar, String> {

    @Autowired
    private roomRepository roomRepos;

    @Override
    public boolean isValid(String s, ConstraintValidatorContext constraintValidatorContext) {
        if(roomRepos.cekroomterdaftar(s)>0){
            return false;
        } else {
            return true;
        }
    }
}
