package com.ldh.view.authority.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ldh.view.authority.entity.AuthorityInformation;
import com.ldh.view.authority.mapper.AuthorityInformationMapper;
import com.ldh.view.authority.service.AuthorityInformationService;
import org.springframework.stereotype.Service;

@Service
public class AuthorityInformationServiceImpl extends ServiceImpl<AuthorityInformationMapper, AuthorityInformation> implements AuthorityInformationService {
}
