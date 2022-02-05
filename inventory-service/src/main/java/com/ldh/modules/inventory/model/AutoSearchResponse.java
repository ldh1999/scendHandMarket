package com.ldh.modules.inventory.model;

import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class AutoSearchResponse implements Serializable {

    private String responseEntity;

}
