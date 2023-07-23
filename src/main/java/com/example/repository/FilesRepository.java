package com.example.repository;

import org.apache.ibatis.annotations.Delete;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import com.example.Entity.FileDB;

/**
 * DB接続用のリポジトリークラス
 */
@Mapper
public interface FilesRepository {
    
    @Insert("insert into file_data (ID, name, type, file_obj) values (#{ID}, #{name}, #{type}, #{file_obj})")
    void save(@Param("ID")int ID, @Param("name")String name, @Param("type")String type, @Param("file_obj")byte[] file_obj);
    //public void save(FileDB fileDB);
    
    @Select("select * from file_data where id = #{id}")
    FileDB findById(int id);
    
    @Delete("delete from file_data where id = #{id}")
    void delete(int id);
    
    @Select("select count(*) from file_data where id = #{id}")
    int countRecord(int id);

}
