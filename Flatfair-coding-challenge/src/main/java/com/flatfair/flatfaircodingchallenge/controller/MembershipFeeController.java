package com.flatfair.flatfaircodingchallenge.controller;

import com.flatfair.flatfaircodingchallenge.entity.OrganisationUnit;
import com.flatfair.flatfaircodingchallenge.entity.RentUnit;
import com.flatfair.flatfaircodingchallenge.repository.OrganisationUnitRepo;
import com.flatfair.flatfaircodingchallenge.service.MembershipFeeInterface;
import com.flatfair.flatfaircodingchallenge.validator.RentUnitValidator;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;


/**
 * @className: MembershipFeeController
 * @author: wenjie.xia
 * @description: TODO
 * @date: 23/08/2022 20:53
 * @version: 1.0
 */
@RestController
@RequestMapping("/Flatfair")
public class MembershipFeeController {

    @Autowired
    MembershipFeeInterface membershipFeeInterface;

    @Autowired
    OrganisationUnitRepo organisationUnitRepo;

    @Autowired
    RentUnitValidator validator;

//    @InitBinder
//    public void initBinder(WebDataBinder binder) {
//        binder.addValidators(validator);
//    }

    @GetMapping("/v1/getMembershipFee")
    public ResponseEntity<Integer> getMembershipFee(@Validated @RequestBody RentUnit rentUnit) {
        validator.validate(rentUnit, null);
        return new ResponseEntity<>(membershipFeeInterface.getMembershipFee(rentUnit), HttpStatus.OK);
    }

    @PostMapping("/v1/addOrganisations")
    public ResponseEntity<HashMap<String, OrganisationUnit>> addOrganisations(@RequestBody List<OrganisationUnit> organisationUnits) {
        return new ResponseEntity<>(organisationUnitRepo.addOrganisations(organisationUnits), HttpStatus.OK);
    }
}
