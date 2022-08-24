package com.flatfair.flatfaircodingchallenge.validator;

import com.flatfair.flatfaircodingchallenge.ExceptionHandler.GlobalException;
import com.flatfair.flatfaircodingchallenge.entity.RentUnit;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.validation.Errors;
import org.springframework.validation.Validator;


/**
 * @className: RentUnitValidator
 * @author: wenjie.xia
 * @description: rent input - function should throw or return an error when the rent amount is outside of the range:
 *              ■ Minimum rent amount is £25 per week or £110 per month
 *              ■ Maximum rent amount is £2000 per week or £8660 per month
 *              ■ rent_period: String - [‘month’, ‘week’]
 * @date: 23/08/2022 22:01
 * @version: 1.0
 */
@Component
public class RentUnitValidator implements Validator {
    @Value("${Minimum_week_rent}")
    private int Minimum_week_rent;

    @Value("${Minimum_month_rent}")
    private int Minimum_month_rent;

    @Value("${Maximum_week_rent}")
    private int Maximum_week_rent;

    @Value("${Maximum_month_rent}")
    private int Maximum_month_rent;

    @Override
    public boolean supports(Class<?> clazz) {
        return RentUnit.class.equals(clazz);
    }

    @Override
    public void validate(Object target, Errors errors) {
        RentUnit rentUnit = (RentUnit) target;
        Integer rentAmount = rentUnit.getRent_amount();
        if(rentAmount == null) {
//            errors.rejectValue("rent_amount", "rent amount can't be empty");
            throw new GlobalException("rent amount can't be empty");
        }
        if (rentAmount < 1 || rentAmount >= Integer.MAX_VALUE) {
//            errors.rejectValue("rent_amount", "rent amount out of range");
            throw new GlobalException("rent amount out of range");
        }
        String period = rentUnit.getRent_period();
        if(period == null) {
//            errors.rejectValue("rent_period", "rent period can't be empty");
            throw new GlobalException("rent period can't be empty");
        }
        if (!"week".equals(period) && !"month".equals(period)) {
//            errors.rejectValue("rent_period", "rent period input illegal");
            throw new GlobalException("rent period input illegal");
        }
        if(("week".equals(period) && rentAmount < Minimum_week_rent)
                || ("month".equals(period) && rentAmount < Minimum_month_rent)) {
//            errors.rejectValue("rent_amount", "rent amount lower than minimum");
            throw new GlobalException("rent amount lower than minimum");
        }
        if(("week".equals(period) && rentAmount > Maximum_week_rent)
                || ("month".equals(period) && rentAmount > Maximum_month_rent)) {
//            errors.rejectValue("rent_amount", "rent amount higher than maximum");
            throw new GlobalException("rent amount higher than maximum");
        }
    }}
