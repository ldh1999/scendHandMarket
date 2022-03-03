package com.ldh.modules.upload.controller;

import com.ldh.modules.upload.constant.FilePath;
import com.ldh.modules.upload.entity.ImageNote;
import com.ldh.modules.upload.model.FileNoteVO;
import com.ldh.modules.upload.model.ImageGetVO;
import com.ldh.modules.upload.model.ImageNoteModel;
import com.ldh.modules.upload.model.ImageNoteVO;
import com.ldh.modules.upload.service.CosImageService;
import com.ldh.modules.upload.service.ImageNoteService;
import com.ldh.otherResourceService.client.ImageNoteClient;
import common.InitUploadModel;
import common.Result;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.transaction.interceptor.TransactionAspectSupport;
import org.springframework.util.ClassUtils;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.context.support.HttpRequestHandlerServlet;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.ServletRequest;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.File;
import java.util.Arrays;
import java.util.LinkedList;
import java.util.List;
import java.util.Random;

@RestController
@Slf4j
@RequestMapping("image/note")
@Api("图片操作")
public class ImageNoteController {

    @Autowired
    private ImageNoteService imageNoteService;

    @Autowired
    private CosImageService cosImageService;

    /**
     * 只适用于windows服务器！！！！
     * @param
     * @param request
     * @return
     */
   /* @ApiOperation(value="图片上传", notes="图片上传")
    @RequestMapping(path = "upload", method = RequestMethod.POST)
    public Result<?> uploadImage(@RequestParam(value = "file") MultipartFile file,
                                 HttpServletRequest request,
                                 HttpServletResponse response,
                                 @RequestParam(name = "imageGroup") String imageGroup,
                                 @RequestParam(name = "objectId") String objectId,
                                 @RequestParam(name = "sort",required = false) Integer sort
    ){

        Result<String> result = new Result<>();

        String fileName = file.getOriginalFilename();
        String fileType = file.getContentType();
        Random random = new Random();
        String randomString = String.valueOf(random.nextInt());
        String fileSaveName = randomString.substring(1,randomString.length());
        String filePathName = fileSaveName+fileName;
        String savePath = FilePath.IMAGE_SAVE_PATH+imageGroup+"/"+filePathName;
        String saveDbPath = FilePath.IMAGE_SHOW_PATH+imageGroup+"/"+filePathName;
        if (!fileType.substring(0,fileType.indexOf("/")).equals("image")){
            result.error("请上传图片");
            return result;
        }
        try{
            File file1 = new File(savePath);
            if (!file1.isDirectory()){
                file1.mkdirs();
            }
            file.transferTo(file1);
            ImageNote imageNote = new ImageNote();
            imageNote.setImgGroup(imageGroup)
                    .setImgName(fileName)
                    .setImgPath(saveDbPath)
                    .setObjectId(objectId)
                    .setSort(sort);
            imageNoteService.save(imageNote);
            result.setResult(this.getNowUrl(request)+imageNote.getImgPath());
            result.setMessage("上传成功");
            result.setSuccess(true);
        }catch (Exception e){
            log.error(e.getMessage());
            TransactionAspectSupport.currentTransactionStatus().setRollbackOnly();
            result.error("上传失败");
        }
        return result;
    }*/

    @ApiOperation(value="图片上传", notes="图片上传")
    @RequestMapping(path = "upload", method = RequestMethod.POST)
    public Result<?> uploadImage(@RequestParam(value = "file") MultipartFile file,
                                 @RequestParam(name = "imageGroup") String imageGroup,
                                 @RequestParam(name = "objectId") String objectId,
                                 @RequestParam(name = "sort",required = false) Integer sort
    ){
        Result<String> result = new Result<>();
        try{
            String saveDbPath = cosImageService.uploadImage(file);
            ImageNote imageNote = new ImageNote();
            imageNote.setImgGroup(imageGroup)
                    .setImgName(file.getOriginalFilename())
                    .setImgPath(saveDbPath)
                    .setObjectId(objectId)
                    .setSort(sort);
            imageNoteService.save(imageNote);
        }catch (Exception e){
            log.error(e.getMessage());
            result.error("上传失败");
        }
        return result;
    }

    @ApiOperation(value="图片批量上传", notes="图片批量上传")
    @RequestMapping(path = "uploadBatch", method = RequestMethod.POST)
    public Result<?> uploadImageBatch(@RequestParam(value = "files") MultipartFile[] files,
                                 @RequestParam(name = "imageGroup") String imageGroup,
                                 @RequestParam(name = "objectId") String objectId){
        Result<String> result = new Result<>();
        try{
            List<ImageNote> imageNoteList = new LinkedList<>();
            for (MultipartFile file : files) {
                ImageNote imageNote = new ImageNote();
                imageNote.setImgGroup(imageGroup)
                        .setImgName(file.getOriginalFilename())
                        .setImgPath(cosImageService.uploadImage(file))
                        .setObjectId(objectId);
                imageNoteList.add(imageNote);
            }
            imageNoteService.saveBatch(imageNoteList);
        }catch (Exception e){
            log.error(e.getMessage());
            result.error("上传失败");
        }
        return result;
    }


    @ApiOperation(value="ant图片展示", notes="ant图片展示")
    @RequestMapping(path = "getFileListByObjectAndGroup", method = RequestMethod.GET)
    public Result<?> getFileListByObjectAndGroup(ImageNoteVO imageNoteVO, ServletRequest request){
        Result<List<?>> result = new Result<>();
        try {
            ImageNote imageNote = new ImageNote();
            imageNote.setObjectId(imageNoteVO.getObjectId())
                    .setImgGroup(imageNoteVO.getImageGroup());
            List<InitUploadModel> list = imageNoteService.getListByGroupAndObjectId(imageNote);
            result.setResult(list);
            result.succcess("");
        }catch (Exception e){
            log.error(e.getMessage(),e);
            result.error(e.getMessage());
        }
        return result;
    }


    @ApiOperation(value="删除", notes="删除")
    @RequestMapping(path = "deletePById", method = RequestMethod.GET)
    public Result<?> deleteById(@RequestParam(name = "uid", required = true)String uid){
        Result<List<?>> result = new Result<>();
        try{
            ImageNote imageNote = imageNoteService.getById(uid);
            imageNoteService.removeById(uid);
            cosImageService.deleteByPath(imageNote.getImgPath());
            result.succcess("删除成功");
        }catch (Exception e){
            log.error(e.getMessage(),e);
            result.error(e.getMessage());
        }
        return result;
    }

    @Deprecated
    @ApiOperation(value="根据object和type获取", notes="根据object和type获取")
    @RequestMapping(path = "getByObjectIdAndImgGroup", method = RequestMethod.GET)
    public Result<?> getByObjectIdAndImgGroup(ImageGetVO imageGetVO){
        Result<List<ImageNoteModel>> result = new Result();
        try{
            if (!imageGetVO.isNotNull()){
                throw new Exception("");
            }
            List<ImageNoteModel> list = imageNoteService.getByObjectIdAndImgGroup(imageGetVO);
            result.setResult(list);
            result.setSuccess(true);
        }catch (Exception e){
            log.error(e.getMessage(), e);
            result.error(e.getMessage());
        }
        return result;
    }

}
