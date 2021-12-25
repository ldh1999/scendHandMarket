package com.ldh.modules.authority.model;

import com.ldh.modules.authority.entity.AuthorityInformation;
import lombok.Data;

import java.io.Serializable;
import java.util.List;

@Data
public class AuthorityInformationModel extends AuthorityInformation implements Serializable {

    private String stsName;

    /** 权限码 */
    private String token;

    /** 角色编码 */
    private String roleNos;
}
