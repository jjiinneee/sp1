package org.zerock.sp1.service;

import org.zerock.sp1.dto.UploadResultDTO;

public interface FileService {
  
  void register(UploadResultDTO uploadResultDTO);
  
  void remove(String uuid);
  
}
