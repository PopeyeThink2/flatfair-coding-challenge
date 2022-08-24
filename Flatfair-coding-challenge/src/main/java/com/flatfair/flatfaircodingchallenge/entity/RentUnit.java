package com.flatfair.flatfaircodingchallenge.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.validator.constraints.Range;

import javax.validation.constraints.NotNull;

/**
 * @className: RentUnit
 * @author: wenjie.xia
 * @description: ○ rent_amount: Integer - rent amount between 1-int.max
 *               ○ rent_period: String - [‘month’, ‘week’]
 *               ○ organisation_unit: OrganisationUnit - branch instance of organisation unit
 * @date: 23/08/2022 21:07
 * @version: 1.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class RentUnit {

    @Range(min=1, max=Integer.MAX_VALUE)
    @NotNull
    Integer rent_amount;

    @NotNull
    String rent_period;

    OrganisationUnit organisation_unit;
}
