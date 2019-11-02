package com.example.demo.service;

import com.example.demo.mapper.LogMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
public class LogService {

    @Autowired
    LogMapper logMapper;

    public  List<Map<String,Object>> getId() {
        return logMapper.getId();
    }


}
