package com.flatfair.flatfaircodingchallenge.repository.impl;

import com.flatfair.flatfaircodingchallenge.entity.OrganisationUnit;
import com.flatfair.flatfaircodingchallenge.repository.OrganisationUnitRepo;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;

/**
 * @className: OrganisationUnitRepoImpl
 * @author: wenjie.xia
 * @description: store temporary organisation objects, can extend to database operations
 * @date: 24/08/2022 01:45
 * @version: 1.0
 */
@Service
public class OrganisationUnitRepoImpl implements OrganisationUnitRepo {
    HashMap<String, OrganisationUnit> organisationMap = new HashMap<String, OrganisationUnit>();

    @Override
    public HashMap<String, OrganisationUnit> addOrganisations(List<OrganisationUnit> organisationUnits) {
        for(OrganisationUnit organisationUnit : organisationUnits) {
            organisationMap.put(organisationUnit.getName(), organisationUnit);
        }
        return organisationMap;
    }

    @Override
    public OrganisationUnit getOrganisation(String name) {
        return organisationMap.get(name);
    }
}
