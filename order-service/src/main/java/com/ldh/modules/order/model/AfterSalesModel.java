package com.ldh.modules.order.model;

import com.ldh.modules.order.entity.AfterSales;
import lombok.Data;

import java.io.Serializable;

@Data
public class AfterSalesModel extends AfterSales implements Serializable {

    private String merchantName;
    private String inventoryName;
    private String firstImage;
}
