package com.example.controller;

import java.util.Base64;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.example.Entity.FileDB;
import com.example.service.FileStorageService;

@Controller
//@RequestMapping("/portfolio/file")
public class FilesController {
  @Autowired  
  private FileStorageService storageService;

  /**
   * 初期表示
   * 
   * @param m
   * @return 表示用のHTMLファイル
   */
  //@GetMapping(value="/index")
  @GetMapping
  public String index(Model m) {
      String message = "";
      try {
          // DBから画像データを取得
          FileDB fileImg = storageService.findById(0);
          // String型でファイル名を取得
          String image = getFileNameOfString(fileImg);
          // view側にレンダリング
          m.addAttribute("image", image);
          return "index";
          // return "file_upload";
      } catch (Exception e) {
          e.printStackTrace();
          message = "Could not find the file";
          m.addAttribute("message", message);
          return "index";
      }
  }
  
  /**
   * 選択した画像データのアップロード
   * 
   * @param file
   * @param m
   * @return 表示用のHTMLファイル
   */
  //@PostMapping("/upload")
  @PostMapping("/portfolio/file/upload")
  public String uploadFile(@RequestParam("file") MultipartFile file, Model m) {
    String message = "";
    try {
      // DBに画像データを保存し、保存した画像データをDBから取得
      FileDB fileImg = storageService.store(file);
      // String型でファイル名を取得
      String image = getFileNameOfString(fileImg);
      // アップロード完了時のメッセージ
      message = "アップロードが完了しました！ ファイル名：" + file.getOriginalFilename();
      // view側にレンダリング
      m.addAttribute("message", message);
      m.addAttribute("image", image);
      return "index";
      //return "file_upload";
    } catch (Exception e) {
      e.printStackTrace();
      message = "Could not upload the file: " + file.getOriginalFilename() + "!";
      m.addAttribute("message", message);
      return "index";
      //return "file_upload";
    }
  }
  
  /**
   * DBから取得したバイナリーデータをString型に変換
   * 
   * @param fileImg
   * @return String型のファイル名
   */
  private String getFileNameOfString(FileDB fileImg) {    
      // 画像データのバイナリーデータを取得
      byte[] bytes = fileImg.getFile_obj();
      // バイナリーデータをBase64(byte[]型をString型)に変換し返却
      return Base64.getEncoder().encodeToString(bytes);
  }
}