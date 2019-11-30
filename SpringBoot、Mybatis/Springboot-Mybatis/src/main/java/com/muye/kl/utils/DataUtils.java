package com.muye.kl.utils;

import com.muye.kl.entity.User;

import java.util.ArrayList;
import java.util.List;

/**
 * @author : gwh
 * @date : 2019-11-26 19:07
 **/
public class DataUtils {
    public  static  List<User> data() {
        List<User> list = new ArrayList<User>();
        for (int i = 0; i < 10; i++) {
            User data = new User();
            data.setId(i);
            data.setName("name"+i);
            data.setAge(i);
            data.setSex("男");
            data.setAddr("北京"+i);
            list.add(data);
        }
        return list;
    }
}
