package com.ldh.orderService.model;

import com.ldh.orderService.pojo.Courier;
import lombok.Data;
import lombok.experimental.Accessors;
import java.io.Serializable;

@Data
public class CourierModel extends Courier implements Serializable {

    /** 快递公司名称 */
    private String courierServicesCompanyName;

}
