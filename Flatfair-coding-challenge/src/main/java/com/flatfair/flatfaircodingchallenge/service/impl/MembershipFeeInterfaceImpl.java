package com.flatfair.flatfaircodingchallenge.service.impl;

import com.flatfair.flatfaircodingchallenge.ExceptionHandler.GlobalException;
import com.flatfair.flatfaircodingchallenge.entity.OrganisationUnit;
import com.flatfair.flatfaircodingchallenge.entity.OrganisationUnitConfig;
import com.flatfair.flatfaircodingchallenge.entity.RentUnit;
import com.flatfair.flatfaircodingchallenge.repository.OrganisationUnitRepo;
import com.flatfair.flatfaircodingchallenge.service.MembershipFeeInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;


/**
 * @className: MembershipFeeInterfaceImpl
 * @author: wenjie.xia
 * @description: TODO
 * @date: 23/08/2022 21:11
 * @version: 1.0
 */
@Service
public class MembershipFeeInterfaceImpl implements MembershipFeeInterface {

    @Value("${VAT}")
    private Double VAT;

    @Value("${Minimum_Fee}")
    private int Minimum_Fee;

    @Autowired
    OrganisationUnitRepo organisationUnitRepo;

    /*
     * @param rentUnit:
     * @return Integer
     * @author wenjiexia
     * @description calculate Membership Fee
     * VAT is 20%
     * Monetary amounts are stored in pence [int]
     * Membership fee is equal to one week of rent + VAT
     *  ○ Minimum membership fee is £120 + VAT - if the rent is lower than £120 the membership fee stays at £120 + VAT
     * @date 24/08/2022 00:18
     */
    @Override
    public Integer getMembershipFee(RentUnit rentUnit) {
        if (rentUnit == null || rentUnit.getRent_amount() == null || rentUnit.getRent_period() == null) {
            throw new GlobalException("Input value missing");
        }
        if(rentUnit.getOrganisation_unit() != null) {
            int fee = getFeeFromOrganisation(rentUnit.getOrganisation_unit());
            if(fee != 0) {
                return fee;
            }
        }
        Integer rent = rentUnit.getRent_amount();
        if("month".equals(rentUnit.getRent_period())) {
            if (rent != null) {
                rent /= 4;
            }
        }
        double fee = rent * VAT + rent;
        DecimalFormat df = new DecimalFormat("#.00");
        fee = fee < Minimum_Fee ? Minimum_Fee : (Double.parseDouble(df.format(fee)) * 100);
        return (int)fee;
    }

    /*
     * @param OrganisationUnit
     * @return Integer
     * @author wenjiexia
     * @description  If the organisation unit has a config object and fixed_membership_fee is true override previous rules
     * and the membership fee should be equal to the fixed_membership_fee_amount.
     * If the passed organisation unit doesn’t have a config recursively check parents
     * (ie branch doesn’t have a config check area, area doesn’t have a config check division)
     * @date 24/08/2022 00:20
     */
    public Integer getFeeFromOrganisation(OrganisationUnit organisationUnit) {
        if(organisationUnit.getConfig() != null) {
            OrganisationUnitConfig config = organisationUnit.getConfig();
            if(config.getHas_fixed_membership_fee()){
                return config.getFixed_membership_fee_amount();
            }
            return getFeeFromOrganisation(organisationUnitRepo.getOrganisation(config.getParent_unit()));
        }
        return 0;
    }
}
