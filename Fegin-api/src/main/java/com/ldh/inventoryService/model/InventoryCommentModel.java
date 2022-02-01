package com.ldh.inventoryService.model;

import com.ldh.inventoryService.pojo.InventoryComment;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class InventoryCommentModel extends InventoryComment implements Serializable {

    private String createByName;

    private String createImgPath;

    /** 这个是昵称 */
    private String replayUserName;

    private String inventoryCode;

    /** usernamee */
    private String createByUserName;

    /** 回复人username */
    private String rUserName;

}
