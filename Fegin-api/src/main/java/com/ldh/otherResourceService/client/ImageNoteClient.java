package com.ldh.otherResourceService.client;

import com.ldh.otherResourceService.model.FileNoteVO;
import common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;

@FeignClient("otherResourceservice")
public interface ImageNoteClient {
    @GetMapping("image/note/getFileListByObjectAndGroup")
    public Result<?> getFileListByObjectAndGroup(FileNoteVO imageNoteVO);
}
