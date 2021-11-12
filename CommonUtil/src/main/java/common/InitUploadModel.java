package common;

import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Slf4j
@Data
public class InitUploadModel implements Serializable {
    private String uid;
    private String name;
    private String url;
    private String sort;
}
