package com.flatfair.flatfaircodingchallenge;

import com.flatfair.flatfaircodingchallenge.entity.OrganisationUnit;
import com.flatfair.flatfaircodingchallenge.entity.OrganisationUnitConfig;
import com.flatfair.flatfaircodingchallenge.entity.RentUnit;
import com.flatfair.flatfaircodingchallenge.service.MembershipFeeInterface;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.junit.jupiter.api.Assertions.assertEquals;

/**
 * @className: WireMockJunitTest
 * @author: wenjie.xia
 * @description: TODO
 * @date: 24/08/2022 00:21
 * @version: 1.0
 */
@SpringBootTest
public class MembershipFeeTest {
    @Autowired
    MembershipFeeInterface membershipFeeInterface;

    @Test
    public void testGetMembershipFeeWithoutConfig() {
        RentUnit rentUnit = new RentUnit();
        rentUnit.setRent_amount(120);
        rentUnit.setRent_period("week");

        Integer fee = membershipFeeInterface.getMembershipFee(rentUnit);
        assertEquals(14400, fee);
    }

    @Test
    public void testGetMembershipFee() {
        RentUnit rentUnit = new RentUnit();
        rentUnit.setRent_amount(120);
        rentUnit.setRent_period("week");

        OrganisationUnit organisationUnit = new OrganisationUnit();
        organisationUnit.setName("branch_b");
        organisationUnit.setConfig(new OrganisationUnitConfig(true, 45000, "area_a"));
        rentUnit.setOrganisation_unit(organisationUnit);

        Integer fee = membershipFeeInterface.getMembershipFee(rentUnit);
        assertEquals(45000, fee);
    }
}
