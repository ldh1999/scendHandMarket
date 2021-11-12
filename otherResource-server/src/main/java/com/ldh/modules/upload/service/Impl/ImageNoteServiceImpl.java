package com.ldh.modules.upload.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ldh.modules.upload.entity.ImageNote;
import com.ldh.modules.upload.mapper.ImageNoteMapper;
import com.ldh.modules.upload.service.ImageNoteService;
import common.InitUploadModel;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ImageNoteServiceImpl extends ServiceImpl<ImageNoteMapper,ImageNote> implements ImageNoteService {

    @Autowired
    private ImageNoteMapper imageNoteMapper;

    @Override
    public List<InitUploadModel> getListByGroupAndObjectId(ImageNote imageNote) {
        return imageNoteMapper.getListByGroupAndObjectId(imageNote);
    }
}
