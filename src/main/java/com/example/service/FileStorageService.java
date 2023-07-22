package com.example.service;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;
import org.springframework.web.multipart.MultipartFile;
import com.example.Entity.FileDB;
import com.example.repository.FilesRepository;

@Service
public class FileStorageService {

  @Autowired
  private FilesRepository filesRepository;

  public FileDB store(MultipartFile file) throws IOException {
    String fileName = StringUtils.cleanPath(file.getOriginalFilename());
    
    // FileDB fileDB = new FileDB(0,fileName, file.getContentType(), file.getBytes());
    
    System.out.println("ID : " + 0);
    System.out.println("fileName : " + fileName);
    System.out.println("type : " + file.getContentType());
    System.out.println("file_obj : " + file.getBytes());
    
    // レコードを削除
    filesRepository.delete(0);
    // 画像を登録
    filesRepository.save(0,fileName, file.getContentType(), file.getBytes());
    
    return filesRepository.findById(0);
  }
  
  public FileDB findById(int id) throws IOException {
      
//      int count = fileDBRepository.countRecord(0);
//      
//      if(count == 0) {
//          return fileDBRepository.findById(0);
//      }
      return filesRepository.findById(id);
  }

}
