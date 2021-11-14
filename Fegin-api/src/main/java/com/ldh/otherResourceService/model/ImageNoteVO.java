package com.ldh.otherResourceService.model;

import com.ldh.otherResourceService.pojo.ImageNote;
import lombok.Data;
import lombok.experimental.Accessors;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Data
@Slf4j
@Accessors(chain = true)
public class ImageNoteVO extends ImageNote implements Serializable {

    private String imageGroup;
}
