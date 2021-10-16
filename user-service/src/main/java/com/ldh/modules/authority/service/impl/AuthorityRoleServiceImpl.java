package com.ldh.modules.authority.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ldh.modules.authority.entity.AuthorityRoleInformation;
import com.ldh.modules.authority.mapper.AuthorityRoleMapper;
import com.ldh.modules.authority.service.AuthorityRoleService;
import org.springframework.stereotype.Service;

@Service
public class AuthorityRoleServiceImpl extends ServiceImpl<AuthorityRoleMapper, AuthorityRoleInformation> implements AuthorityRoleService {

}
