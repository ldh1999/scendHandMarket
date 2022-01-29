package model;

import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;
import java.io.Serializable;

@Data
@Slf4j
@Accessors(chain = true)
public class UpLoadImageMqModel implements Serializable {

    private static final long serialVersionUID = -7254888630210798460L;
    /** 文件组 */
    private String imgGroup;
    /** 文件名 */
    private String imgName;
    /** 对象id */
    private String objectId;

    private String fileJson;
}
