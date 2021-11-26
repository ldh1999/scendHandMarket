package com.ldh.modules.authority.model;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Accessors(chain = true)
public class EditPasswordVO {

    private String originPassword;

    private String password;

    private String authorityId;
}
