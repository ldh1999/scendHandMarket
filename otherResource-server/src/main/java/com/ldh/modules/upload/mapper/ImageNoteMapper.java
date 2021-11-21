package com.ldh.modules.upload.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ldh.modules.upload.entity.ImageNote;
import com.ldh.modules.upload.model.ImageGetVO;
import com.ldh.modules.upload.model.ImageListGetVO;
import com.ldh.modules.upload.model.ImageNoteModel;
import common.InitUploadModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ImageNoteMapper extends BaseMapper<ImageNote> {


    List<InitUploadModel> getListByGroupAndObjectId(@Param("imageNote") ImageNote imageNote);

    List<ImageNoteModel> getByObjectIdAndImgGroup(@Param("imageGetVO") ImageGetVO imageGetVO);

    /** 根据list反list */
    List<ImageNoteModel> getByObjectIdAndImgGroupList(@Param("imageGetVO") ImageListGetVO imageGetVO);
}
