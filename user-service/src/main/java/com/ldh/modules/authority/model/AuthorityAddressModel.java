package com.ldh.modules.authority.model;

import com.ldh.modules.authority.entity.AuthorityAddress;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class AuthorityAddressModel extends AuthorityAddress implements Serializable {
}
