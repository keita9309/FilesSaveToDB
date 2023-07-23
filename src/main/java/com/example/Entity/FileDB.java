package com.example.Entity;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.Lob;
import org.hibernate.annotations.Type;
import org.springframework.data.annotation.Id;
import org.springframework.data.relational.core.mapping.Column;
import org.springframework.data.relational.core.mapping.Table;
import org.springframework.web.multipart.MultipartFile;
import lombok.AllArgsConstructor;
import lombok.Data;

/**
 * DB登録用のEntityクラス
 */
@Data
@AllArgsConstructor
public class FileDB implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    private int ID;
    
    private String name;
    
    private String type;
    
    private byte[] file_obj;
    
}
