package com.ldh.modules.inventory.model;

import com.ldh.modules.inventory.entity.InventoryComment;
import lombok.Data;
import lombok.experimental.Accessors;

import java.io.Serializable;

@Data
@Accessors(chain = true)
public class InventoryCommentModel extends InventoryComment implements Serializable {

    private String createByName;

    private String createImgPath;

    private String replayUserName;

}
