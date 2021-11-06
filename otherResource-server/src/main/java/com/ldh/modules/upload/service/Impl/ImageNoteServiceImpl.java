package com.ldh.modules.upload.service.Impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ldh.modules.upload.entity.ImageNote;
import com.ldh.modules.upload.mapper.ImageNoteMapper;
import com.ldh.modules.upload.service.ImageNoteService;
import org.springframework.stereotype.Service;

@Service
public class ImageNoteServiceImpl extends ServiceImpl<ImageNoteMapper,ImageNote> implements ImageNoteService {
}
