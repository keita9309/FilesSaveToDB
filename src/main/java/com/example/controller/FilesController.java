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

  //@GetMapping(value="/index")
  @GetMapping
  public String index(Model m) {
      String message = "";
      String path = "";
      try {
          // DBから画像データを取得
          FileDB fileImg = storageService.findById(0);
          // 画像データの中のバイナリーコードを取得
          byte[] bytes = fileImg.getFile_obj();
          // byte[]型をString型に変換
          String image = Base64.getEncoder().encodeToString(bytes);
          // viewにレンダリング
          m.addAttribute("image", image);
          m.addAttribute("path", path);
          return "index";
          // return "file_upload";
      } catch (Exception e) {
          e.printStackTrace();
          message = "Could not find the file";
          m.addAttribute("message", message);
          return "index";
      }
  }

  //@PostMapping("/upload")
  @PostMapping("/portfolio/file/upload")
  public String uploadFile(@RequestParam("file") MultipartFile file, Model m) {
    String message = "";
    try {
      // DBから画像データを取得
      FileDB savedFile = storageService.store(file);
      // 画像データの中のバイナリーコードを取得
      byte[] bytes = savedFile.getFile_obj();
      // byte[]型をString型に変換
      String image =  Base64.getEncoder().encodeToString(bytes);
      // アップロードが完了した時のメッセージ
      message = "アップロードが完了しました！ ファイル名：" + file.getOriginalFilename();
      // viewにレンダリング
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
}