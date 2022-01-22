package com.ldh.modules.user.service;

import com.ldh.modules.user.model.CourierOrderInformationModel;
import com.ldh.modules.user.model.ManagementInformationModel;

import java.util.concurrent.ExecutionException;

public interface SysUserService {

    ManagementInformationModel getInformationForManagement() throws ExecutionException, InterruptedException;

    CourierOrderInformationModel getCourierOrderInformation() throws ExecutionException, InterruptedException;
}
