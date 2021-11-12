package com.ldh.modules.upload.model;

import com.ldh.modules.upload.entity.FileNote;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;

import java.io.Serializable;

@Slf4j
@Data
public class FileNoteVO extends FileNote implements Serializable {


    private String imageGroup;
}

