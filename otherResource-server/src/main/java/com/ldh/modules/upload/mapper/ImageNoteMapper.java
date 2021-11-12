package com.ldh.modules.upload.mapper;


import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.ldh.modules.upload.entity.ImageNote;
import common.InitUploadModel;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import java.util.List;

@Mapper
public interface ImageNoteMapper extends BaseMapper<ImageNote> {

    List<InitUploadModel> getListByGroupAndObjectId(@Param("imageNote") ImageNote imageNote);
}
