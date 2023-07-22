package com.example.controller;

import java.util.Base64;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.example.Entity.FileDB;
import com.example.service.FileStorageService;
import lombok.RequiredArgsConstructor;

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
          FileDB fileImg = storageService.findById(0);
          byte[] bytes = fileImg.getFile_obj();
          // ポイント4: Base64.getEncoder().encodeToString(bytes)でbyteをStringにして、Viewに渡す
          String image = Base64.getEncoder().encodeToString(bytes);
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
      FileDB savedFile = storageService.store(file);
      byte[] bytes = savedFile.getFile_obj();
      // ポイント4: Base64.getEncoder().encodeToString(bytes)でbyteをStringにして、Viewに渡す
      String image =  Base64.getEncoder().encodeToString(bytes);
      message = "Uploaded the file successfully: " + file.getOriginalFilename();
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