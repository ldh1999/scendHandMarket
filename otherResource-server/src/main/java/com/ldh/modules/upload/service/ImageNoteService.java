package com.ldh.modules.upload.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ldh.modules.upload.entity.ImageNote;
import common.InitUploadModel;
import org.apache.ibatis.annotations.Param;

import java.util.List;

public interface ImageNoteService extends IService<ImageNote> {

    List<InitUploadModel> getListByGroupAndObjectId(@Param("imageNote") ImageNote imageNote);

}
