package com.flatfair.flatfaircodingchallenge.repository;

import com.flatfair.flatfaircodingchallenge.entity.OrganisationUnit;
import org.springframework.stereotype.Repository;

import java.util.HashMap;
import java.util.List;

/**
 * @className: OrganisationUnitRepo
 * @author: wenjie.xia
 * @description: TODO
 * @date: 24/08/2022 00:42
 * @version: 1.0
 */
@Repository
public interface OrganisationUnitRepo {
    HashMap<String, OrganisationUnit> addOrganisations(List<OrganisationUnit> organisationUnits);

    OrganisationUnit getOrganisation(String name);
}
