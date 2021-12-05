package com.ldh.userService.model;

import com.ldh.userService.pojo.AuthorityAddress;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class AuthorityAddressModel extends AuthorityAddress implements Serializable {
}
