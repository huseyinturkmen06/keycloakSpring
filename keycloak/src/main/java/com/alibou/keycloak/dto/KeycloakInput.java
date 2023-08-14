package com.alibou.keycloak.dto;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Id;

@Data
public class KeycloakInput {


    private String client_id;

    private String tel_no;

    private String operator;
}
