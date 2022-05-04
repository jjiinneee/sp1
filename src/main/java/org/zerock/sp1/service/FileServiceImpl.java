package org.zerock.sp1.service;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;
import org.zerock.sp1.domain.AttachFile;
import org.zerock.sp1.dto.UploadResultDTO;
import org.zerock.sp1.mapper.FileMapper;

@Service
@Log4j2
@RequiredArgsConstructor
public class FileServiceImpl implements FileService{
  
  private final FileMapper fileMapper;
  private final ModelMapper modelMapper;
  
  
  @Override
  public void register(UploadResultDTO uploadResultDTO){
    //UploadResultDTO => AttachFile
    AttachFile attachFile = modelMapper.map(uploadResultDTO, AttachFile.class);
    fileMapper.insert(attachFile);
  }
  
  @Override
  public void remove(String uuid){
    fileMapper.delete(uuid);
  }
}
