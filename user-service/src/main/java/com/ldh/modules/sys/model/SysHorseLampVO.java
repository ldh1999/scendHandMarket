package com.ldh.modules.sys.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.ldh.modules.sys.entity.SysHorseLamp;
import lombok.Data;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.multipart.MultipartFile;

import java.io.Serializable;

@Data
@Slf4j
public class SysHorseLampVO extends SysHorseLamp implements Serializable {

}
