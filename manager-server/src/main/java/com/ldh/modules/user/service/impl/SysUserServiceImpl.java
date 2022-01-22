package com.ldh.modules.user.service.impl;

import com.ldh.inventoryService.client.MerchantClient;
import com.ldh.modules.user.model.CourierOrderInformationModel;
import com.ldh.modules.user.model.ManagementInformationModel;
import com.ldh.modules.user.service.SysUserService;
import com.ldh.orderService.client.OrderInformationClient;
import com.ldh.orderService.client.OrderPhysicalDistributionClient;
import com.ldh.security.entity.SysUserEntity;
import com.ldh.userService.client.AuthorityClient;
import constant.OrderPhysicalEnum;
import constant.TimeConstant;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Service;

import java.util.concurrent.*;

@Service
public class SysUserServiceImpl implements SysUserService {

    @Autowired
    private MerchantClient merchantClient;

    @Autowired
    private AuthorityClient authorityClient;

    @Autowired
    private OrderInformationClient orderInformationClient;

    @Autowired
    private OrderPhysicalDistributionClient orderPhysicalDistributionClient;

    @Override
    public ManagementInformationModel getInformationForManagement() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        Future<Integer> merchantSum = executorService.submit(new MerchantTask(null));
        Future<Integer> merchantWeek = executorService.submit(new MerchantTask(TimeConstant.WEEK));
        Future<Integer> orderSum = executorService.submit(new OrderTask(null));
        Future<Integer> orderWeek = executorService.submit(new OrderTask(TimeConstant.WEEK));
        Future<Integer> orderDay = executorService.submit(new OrderTask(TimeConstant.DAY));
        Future<Integer> userSum = executorService.submit(new UserTask(null));
        Future<Integer> userWeek = executorService.submit(new UserTask(TimeConstant.WEEK));
        ManagementInformationModel managementInformationModel = new ManagementInformationModel();
        managementInformationModel
                .setMerchantSum(merchantSum.get())
                .setMerchantWeek(merchantWeek.get())
                .setOrderDay(orderDay.get())
                .setOrderSum(orderSum.get())
                .setUserSum(userSum.get())
                .setUserWeek(userWeek.get())
                .setOrderDayPrice(orderDay.get()*100/orderWeek.get());
        return managementInformationModel;
    }

    @Override
    public CourierOrderInformationModel getCourierOrderInformation() throws ExecutionException, InterruptedException {
        ExecutorService executorService = Executors.newCachedThreadPool();
        SysUserEntity sysUserEntity = (SysUserEntity)SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        Future<Integer> courierOrderSum = executorService.submit(new CourierTask(null, sysUserEntity.getUsername()));
        Future<Integer> courierOrderSended = executorService.submit(new CourierTask(OrderPhysicalEnum.sended.getOrderPhysical(),sysUserEntity.getUsername()));
        Future<Integer> courierOrderSending = executorService.submit(new CourierTask(OrderPhysicalEnum.sending.getOrderPhysical(),sysUserEntity.getUsername()));
        CourierOrderInformationModel courierOrderInformationModel = new CourierOrderInformationModel();
        courierOrderInformationModel
                .setOrderSum(courierOrderSum.get())
                .setOrderFinally(courierOrderSended.get())
                .setOrderSending(courierOrderSending.get());
        return courierOrderInformationModel;
    }



    private class MerchantTask implements Callable<Integer>{

        private String time;

        public MerchantTask(String time) {
            this.time = time;
        }

        @Override
        public Integer call() throws Exception {
            return (Integer) merchantClient.getMerchantCountByObject(time).getResult();
        }
    }


    private class OrderTask implements Callable<Integer>{


        private String time;

        public OrderTask(String time) {
            this.time = time;
        }

        @Override
        public Integer call() throws Exception {
            return (Integer) orderInformationClient.getOrderCountByObject(time).getResult();
        }
    }

    private class UserTask implements Callable<Integer>{

        private String time;

        public UserTask(String time) {
            this.time = time;
        }
        @Override
        public Integer call() throws Exception {
            return (Integer) authorityClient.getUserCountByObject(time).getResult();
        }
    }


    private class CourierTask implements Callable<Integer>{

        private String stsType;

        private String courierCode;

        public CourierTask(String stsType, String courierCode) {
            this.stsType = stsType;
            this.courierCode = courierCode;
        }

        @Override
        public Integer call() throws Exception {
            return (Integer) orderPhysicalDistributionClient.countOrderByObj(courierCode, stsType).getResult();
        }
    }
}
