package com.ldh.modules.user.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class CourierOrderInformationModel implements Serializable {

    private Integer orderSum;
    private Integer orderFinally;
    private Integer orderSending;
}
