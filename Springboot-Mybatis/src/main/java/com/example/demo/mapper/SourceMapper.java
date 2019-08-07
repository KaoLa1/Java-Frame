package com.example.demo.mapper;

import com.example.demo.entity.Source;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public interface SourceMapper {
     List<Map<String,Object>> getSource();

     Source  insertSource(Source source);

}
