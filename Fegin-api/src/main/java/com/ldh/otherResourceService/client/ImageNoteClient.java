package com.ldh.otherResourceService.client;

import com.ldh.otherResourceService.model.FileNoteVO;
import com.ldh.otherResourceService.model.ImageNoteVO;
import common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("otherResourceservice")
public interface ImageNoteClient {
    @GetMapping("image/note/getFileListByObjectAndGroup")
    Result<?> getFileListByObjectAndGroup(@SpringQueryMap ImageNoteVO imageNoteVO);
}
