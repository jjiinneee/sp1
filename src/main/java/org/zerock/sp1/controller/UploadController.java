package org.zerock.sp1.controller;

import lombok.extern.log4j.Log4j2;
import net.coobird.thumbnailator.Thumbnails;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.util.FileCopyUtils;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import org.zerock.sp1.dto.UploadResultDTO;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@Log4j2
public class UploadController {



//  @GetMapping("/view/{fileName : .+ }")
//  public void viewFile(@PathVariable("fileName") String fileName){
  @GetMapping("/view")
  public ResponseEntity<byte[]> viewFile(String fileName){
    log.info("==============");
    log.info("fileName -------" + fileName);
    
    File targetFile = new File("/Users/eunjin/upload/"+fileName);
  
    try {
      String mimeType = Files.probeContentType(targetFile.toPath());
      byte[] data = FileCopyUtils.copyToByteArray(targetFile);
      return ResponseEntity.ok().header("content-Type",mimeType).body(data);
    } catch (IOException e) {
      e.printStackTrace();
      return ResponseEntity.status(404).build();
    }
    
  }
  
  
  @PostMapping("/delete")
  @ResponseBody
  public Map<String, String> fileDelete(String fileName){
    int idx = fileName.lastIndexOf("/");
    String path = fileName.substring(0,idx);
    String name = fileName.substring(idx+1);
    log.info("path" + path);
    log.info("name" + name);
    File targetFile = new File("/Users/eunjin/upload/" + fileName);
    
    boolean result = targetFile.delete();
    
    //thumbnail
    if(result){
      File thumbFile = new File("/Users/eunjin/upload/"+path +"s_"+name);
      thumbFile.delete();
    }
    return Map.of("result", "success");
  }
  @PostMapping("/upload1")
  @ResponseBody
  public List<UploadResultDTO> upload1(MultipartFile[] files){
    log.info("=========================");
    log.info(files);
    
    List<UploadResultDTO>  list = new ArrayList<>();
    
    
    
    //업로드된 파일이 있따고 가정
    for (MultipartFile file : files) {
      
      String originalFileName = file.getOriginalFilename();
      
      log.info("contentType",file.getContentType());
      
      boolean img = file.getContentType().startsWith("image");
      
      String uuid = UUID.randomUUID().toString();
      
      String saveName = uuid +"_"+originalFileName;
      
      
      log.info("orgfileName",file.getOriginalFilename());
      log.info("size",file.getSize());
      log.info("resource",file.getResource());
      log.info(makeFolders());
      String saveFolder =  makeFolders();
      File saveFile = new File("/Users/eunjin/upload/"+saveFolder+"/"+saveName);
      
      
      try(InputStream in = file.getInputStream();
          FileOutputStream fos = new FileOutputStream("/Users/eunjin/upload/" + saveFolder +"/" + saveName);
      ) {
        FileCopyUtils.copy(in, fos);
        
        
      } catch (IOException e) {
        e.printStackTrace();
      }
      
      if(img){
        String thumFileName = saveFolder + "/s_" +  saveName;
        
        File thumFile = new File("/Users/eunjin/upload/" + thumFileName);
  
        try {
          Thumbnails.of(saveFile)
                  .size(200, 200)
                  .toFile(thumFile);
        } catch (IOException e) {
          throw new RuntimeException(e);
        }
      }
  
      list.add(UploadResultDTO.builder()
                      .orgFileName(originalFileName)
                      .uuid(uuid)
                      .filePath(saveFolder)
              .build());
      
      log.info("------------");
    }
    return list;
  }
  
  public String makeFolders(){
    SimpleDateFormat sdf =  new SimpleDateFormat("yyyy/mm/dd");
    
    String str = sdf.format(new Date());
    
    File folderPath = new File("/Users/eunjin/upload/" + str);
    
    if(!folderPath.exists()){
      folderPath.mkdirs();
    }
    return str;
  }
}
