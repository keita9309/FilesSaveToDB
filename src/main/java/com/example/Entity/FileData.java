package com.example.Entity;

import java.io.InputStream;
import java.io.Serializable;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * ダウンロード用のEntityクラス
 */
@Data
@AllArgsConstructor
public class FileData implements Serializable {

    private static final long serialVersionUID = 1L;
    
    private int ID;
    
    private String name;
    
    private String type;
    
    // ダウンロード用のフィールド
    private InputStream inputFile_obj;

}
