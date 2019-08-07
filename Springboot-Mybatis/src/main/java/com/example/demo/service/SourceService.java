package com.example.demo.service;

import com.example.demo.entity.Source;
import com.example.demo.mapper.SourceMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class SourceService {

    @Autowired
    SourceMapper sourceMapper;

    public List<Map<String,Object>>  getSource(){
        return sourceMapper.getSource();
    }

    public Source  insertSource(Source source){
        return sourceMapper.insertSource(source);
    }

}
