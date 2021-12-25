package com.ldh.userService.model;

import com.ldh.userService.pojo.AuthorityRoleInformation;
import lombok.Data;

import java.io.Serializable;

@Data
public class AuthorityRoleInformationModel extends AuthorityRoleInformation implements Serializable{
    /** 权限名称*/
    private String sysRoleName;
    /** 权限编码*/
    private String sysRoleNo;
}
