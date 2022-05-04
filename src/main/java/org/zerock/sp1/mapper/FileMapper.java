package org.zerock.sp1.mapper;

import org.zerock.sp1.domain.AttachFile;

public interface FileMapper {
  
  void insert(AttachFile attachFile);
  
  void delete(String uuid);
}
