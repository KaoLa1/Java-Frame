package com.muye.kl.demo.utils;

import com.muye.kl.demo.constant.BaseContent;
import com.muye.kl.demo.entity.po.Person;
import lombok.extern.slf4j.Slf4j;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : gwh
 * @date : 2019-11-26 19:07
 **/
@Slf4j
public class DataUtils {

    public  static  List<Person> data() {
        List<Person> list = new ArrayList<>();
        List<List<String>> dataList = new ArrayList<>();
         long beginTime = System.currentTimeMillis();
        for (int i = 0; i < BaseContent.NUM; i++) {
            Person data = new Person();
            data.setId("id"+i);
            data.setName("name"+i);
            data.setAge("age"+i);
            data.setSex("男");
            data.setAddr("北京"+i);
            list.add(data);
//            dataList.add(list);
        }
        long endTime = System.currentTimeMillis();
        log.info("用时:"+(endTime-beginTime));
        return list;
    }
}
