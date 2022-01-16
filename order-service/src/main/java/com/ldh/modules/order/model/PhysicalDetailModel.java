package com.ldh.modules.order.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.Date;
import java.util.List;

@Data
@Accessors(chain = true)
public class PhysicalDetailModel implements Serializable {

    private String orderId;

    private String orderPhysicalDistributionId;

    private String startPositionName;

    private String endPositionName;

    private String orderSts;

    private String courierServicesName;

    private String PhySts;

    private String courierName;

    private String courierPhone;

    private List<Phy> phyList;

    @Data
    public class Phy{
        private String nowPositionName;
        private String nextPositionName;
        private Date createTime;
    }

    public Phy getPhyC(){
        return new Phy();
    }
}
