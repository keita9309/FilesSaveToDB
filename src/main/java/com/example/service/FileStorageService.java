package com.example.service;

import java.io.IOException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import com.example.Entity.FileDB;
import com.example.repository.FilesRepository;

/**
 * 初期表示とアップロード時の処理
 */
@Service
public class FileStorageService {

  @Autowired
  private FilesRepository filesRepository;
  
  /**
   * Viewで選択したアップロード対象の画像データをDBに保存
   * 保存した画像データを取得し返却
   * 
   * @param file
   * @return 保存した画像データ
   * @throws IOException
   */
  public FileDB store(MultipartFile file) throws IOException {
    
    // System.out.println("ID : " + 0);
    // System.out.println("fileName : " + file.getOriginalFilename());
    // System.out.println("type : " + file.getContentType());
    // System.out.println("file_obj : " + file.getBytes());
    
    // 現在表示している画像データを削除
    filesRepository.delete(0);
    // アップロード対象の画像データをDBに登録
    filesRepository.save(0,file.getOriginalFilename(), file.getContentType(), file.getBytes());
    // 直前にDBに保存した画像データを取得し返却
    return filesRepository.findById(0);
  }
  
  /**
   * 初期表示用の画像データを取得し返却
   * 
   * @param id
   * @return DBから取得した画像データ(保存済み)
   * @throws IOException
   */
  public FileDB findById(int id) throws IOException {
    // 画像データを取得し返却
    return filesRepository.findById(id);
  }

}
