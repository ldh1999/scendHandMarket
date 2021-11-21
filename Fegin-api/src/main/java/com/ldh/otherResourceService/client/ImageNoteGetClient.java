package com.ldh.otherResourceService.client;

import com.ldh.otherResourceService.model.ImageGetVO;
import com.ldh.otherResourceService.model.ImageListGetVO;
import com.ldh.otherResourceService.model.ImageNoteModel;
import common.Result;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.cloud.openfeign.SpringQueryMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@FeignClient("otherResourceservice/imageNote/getOut")
public interface ImageNoteGetClient {

    @RequestMapping(path = "getByObjectIdAndImgGroup", method = RequestMethod.GET)
    Result<List<ImageNoteModel>> getByObjectIdAndImgGroup(@SpringQueryMap ImageGetVO imageGetVO);

    @RequestMapping(path = "getByObjectIdAndImgGroupList", method = RequestMethod.GET)
    Result<List<ImageNoteModel>> getByObjectIdAndImgGroupList(@SpringQueryMap ImageListGetVO imageListGetVO);


}
