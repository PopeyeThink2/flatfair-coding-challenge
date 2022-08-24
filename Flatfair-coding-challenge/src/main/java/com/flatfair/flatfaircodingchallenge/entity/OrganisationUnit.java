package com.flatfair.flatfaircodingchallenge.entity;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * @className: OrganisationUnit
 * @author: wenjie.xia
 * @description: ○ name: String
 *               ○ config: OrganisationUnitConfig
 * @date: 23/08/2022 20:46
 * @version: 1.0
 */
@Data
@JsonIgnoreProperties(ignoreUnknown = true)
@AllArgsConstructor
@NoArgsConstructor
public class OrganisationUnit {
    String name;
    OrganisationUnitConfig config;
}
