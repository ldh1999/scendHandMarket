package common;

import lombok.Data;

import java.io.Serializable;

@Data
public class OptionModel implements Serializable {

    public String key;
    public String title;
    public String value;
}
