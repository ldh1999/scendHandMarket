package com.ldh.modules.upload.model;

import com.ldh.modules.upload.entity.ImageNote;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Data
@Slf4j
public class ImageNoteVO extends ImageNote implements Serializable {

    private String imageGroup;
}
