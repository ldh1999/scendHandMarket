package com.ldh.modules.order.model;

import com.ldh.modules.order.entity.AfterSales;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;
import java.util.List;

@Data
@Accessors(chain = true)
public class AfterSalesDetailsModel extends AfterSales implements Serializable {

    private String userName;
    private String userPhone;
    private String userAuthorityName;

    private String inventoryName;
    private String inventoryCode;

    private List<String> reasonImageList;
}
