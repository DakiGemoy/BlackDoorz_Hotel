package com.Asset.BlackDoorzHotel.Validation;

import com.Asset.BlackDoorzHotel.DTO.Transaction.TransactionInsertDto;
import net.bytebuddy.asm.Advice;
import org.springframework.beans.BeanWrapperImpl;

import javax.validation.ConstraintValidator;
import javax.validation.ConstraintValidatorContext;
import java.time.LocalDate;

public class CheckCekoutValidator implements ConstraintValidator<checkCekout, Object> {

    private String cekin;
    private String cekot;

    @Override
    public void initialize(checkCekout constraintAnnotation) {
        this.cekin = constraintAnnotation.CheckIn();
        this.cekot = constraintAnnotation.CheckOut();
    }

    @Override
    public boolean isValid(Object o, ConstraintValidatorContext constraintValidatorContext) {
        TransactionInsertDto asda = (TransactionInsertDto) (o);
        if(asda.getCekin() == null || asda.getCekout() == null){
            return false;
        } else {
            LocalDate Cekin = LocalDate.parse(new BeanWrapperImpl(o).getPropertyValue(cekin).toString());
            LocalDate Cekout = LocalDate.parse(new BeanWrapperImpl(o).getPropertyValue(cekot).toString());
            LocalDate hariini = LocalDate.now();
            if(Cekout.isBefore(hariini)){
                return false;
            } else {
                if(Cekout.isEqual(Cekin) || Cekout.isAfter(Cekin)){
                    return true;
                } else {
                    return false;
                }
            }
        }

    }
}
