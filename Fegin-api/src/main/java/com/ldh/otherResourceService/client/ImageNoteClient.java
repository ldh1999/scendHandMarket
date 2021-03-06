package com.ldh.otherResourceService.client;

import com.ldh.otherResourceService.model.FileNoteVO;
import com.ldh.otherResourceService.model.ImageGetVO;
import com.ldh.otherResourceService.model.ImageNoteModel;
import com.ldh.otherResourceService.model.ImageNoteVO;
import common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.List;

@FeignClient("otherResourceservice/image/note/")
public interface ImageNoteClient {

    /** ant图片展示 */
    @GetMapping("getFileListByObjectAndGroup")
    Result<?> getFileListByObjectAndGroup(@SpringQueryMap ImageNoteVO imageNoteVO);

    @Deprecated
    @RequestMapping(path = "getByObjectIdAndImgGroup", method = RequestMethod.GET)
    Result<List<ImageNoteModel>> getByObjectIdAndImgGroup(@SpringQueryMap ImageGetVO imageGetVO);
}
