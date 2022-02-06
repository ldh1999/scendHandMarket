package com.ldh.rabbitmq.service;

public interface InventoryPreferencesRabbitMQService {

    void updatePreferences(String inventoryId, String userId, String operation);
}
