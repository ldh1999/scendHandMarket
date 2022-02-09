package com.ldh.listenter.shop;

import com.ldh.constant.ShopPreferencesChange;
import com.ldh.modules.inventory.service.InventoryCategoryService;
import com.ldh.modules.shop.service.ShopPreferencesTypeService;
import constant.AmqpConstant;
import constant.UserOperationConstant;
import org.springframework.amqp.core.ExchangeTypes;
import org.springframework.amqp.rabbit.annotation.Exchange;
import org.springframework.amqp.rabbit.annotation.Queue;
import org.springframework.amqp.rabbit.annotation.QueueBinding;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.Map;

@Component
public class ShopPreferencesTypeListener {

    @Autowired
    private ShopPreferencesChange shopPreferencesChange;

    @Autowired
    private ShopPreferencesTypeService shopPreferencesTypeService;

    @Autowired
    private InventoryCategoryService inventoryCategoryService;

    @RabbitListener(bindings = @QueueBinding(
            value = @Queue(name = AmqpConstant.PREFERENCES_OPERATION_QUEUE),
            exchange = @Exchange(name = AmqpConstant.PREFERENCES_EXCHANGE, type = ExchangeTypes.TOPIC)
    ))
    public void insert(Map<String, String> map) {
        if(!map.containsKey("inventoryId")&&!map.containsKey("userId")&&!map.containsKey("operation")){
           return;
        }
        String inventoryId = map.get("inventoryId");
        String userId = map.get("userId");
        String operation = map.get("operation");
        String type = inventoryCategoryService.getFatherCategoryIdsByInventoryId(inventoryId);
        String[] typeIds = type.split(",");
        switch (operation) {
            case UserOperationConstant.LOOK:
                shopPreferencesTypeService.increasesValue(typeIds, shopPreferencesChange.getLook(), userId);
                break;
            case UserOperationConstant.JOIN_TROLLEY:
                shopPreferencesTypeService.increasesValue(typeIds, shopPreferencesChange.getJoinTrolley(), userId);
                break;
            case UserOperationConstant.BUY:
                shopPreferencesTypeService.setMedianToTypeId(typeIds, userId);
                break;
            default:
                break;
        }
    }
}