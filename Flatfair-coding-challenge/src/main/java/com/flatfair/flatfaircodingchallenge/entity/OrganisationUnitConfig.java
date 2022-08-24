package com.flatfair.flatfaircodingchallenge.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * @className: OrganisationUnitConfig
 * @author: wenjie.xia
 * @description: ○ has_fixed_membership_fee: Boolean
 *               ○ fixed_membership_fee_amount: Integer
 *                  ○ parent_unit: OrganisationUnit
 *                  ○ children_unit: List<OrganisationUnit>
 * @date: 23/08/2022 20:46
 * @version: 1.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class OrganisationUnitConfig {
    Boolean has_fixed_membership_fee;
    Integer fixed_membership_fee_amount;
    String parent_unit;
}
