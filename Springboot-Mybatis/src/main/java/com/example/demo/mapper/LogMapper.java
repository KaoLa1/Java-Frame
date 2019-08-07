package com.example.demo.mapper;


import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Mapper
@Repository
public  interface LogMapper {
     List<Map<String,Object>> getId();
}
