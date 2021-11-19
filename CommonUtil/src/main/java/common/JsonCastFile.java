package common;

import com.alibaba.fastjson.JSONObject;
import org.springframework.web.multipart.MultipartFile;

public class JsonCastFile {

    public static String toJson(MultipartFile multipartFile){
        return JSONObject.toJSON(multipartFile).toString();
    }

    public static MultipartFile toFile(String json){
        return (MultipartFile)JSONObject.parse(json);
    }
}
