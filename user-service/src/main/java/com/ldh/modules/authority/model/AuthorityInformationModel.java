package com.ldh.modules.authority.model;

import com.ldh.modules.authority.entity.AuthorityInformation;
import lombok.Data;

import java.io.Serializable;

@Data
public class AuthorityInformationModel extends AuthorityInformation implements Serializable {

    public String stsName;
}
