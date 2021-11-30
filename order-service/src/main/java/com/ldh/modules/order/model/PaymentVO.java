package com.ldh.modules.order.model;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Data
@Accessors(chain = true)
public class PaymentVO {
    String orderId;
}
