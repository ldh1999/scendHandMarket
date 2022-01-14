package com.ldh.modules.informationMaintenance.model;

import com.ldh.modules.informationMaintenance.entity.Courier;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class CourierModel extends Courier implements Serializable {

    /** 快递公司名称 */
    private String courierServicesCompanyName;

}
