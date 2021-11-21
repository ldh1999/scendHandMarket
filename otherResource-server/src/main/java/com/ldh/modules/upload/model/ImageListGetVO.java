package com.ldh.modules.upload.model;

import io.swagger.annotations.ApiModel;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;
import java.util.List;

@Slf4j
@Data
@Accessors(chain = true)
@ApiModel(value="蹄片对外接口VO", description="蹄片对外接口VO")
public class ImageListGetVO implements Serializable {
    private List<String> objectId;
    private String imgGroup;
    private Integer sort;
}
