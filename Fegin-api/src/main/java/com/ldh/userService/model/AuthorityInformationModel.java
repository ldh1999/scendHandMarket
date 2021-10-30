package com.ldh.userService.model;

import com.ldh.userService.pojo.AuthorityInformation;
import lombok.Data;

import java.io.Serializable;

@Data
public class AuthorityInformationModel extends AuthorityInformation implements Serializable {

    public String stsName;

    /** 权限码 */
    private String token;
}
