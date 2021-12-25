package com.ldh.userService.model;

import com.ldh.userService.pojo.AuthorityInformation;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AuthorityInformationModel extends AuthorityInformation implements Serializable {



    public String stsName;

    /** 权限码 */
    private String token;

    /** 角色编码 */
    private String roleNos;
}
