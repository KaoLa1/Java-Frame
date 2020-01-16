package com.example.demo.utils;

import lombok.extern.slf4j.Slf4j;
import org.junit.Test;

import java.util.Arrays;
import java.util.List;

/**
 * @author : gwh
 * @date : 2020-01-16 15:35
 **/
@Slf4j
public class StringTest {

    @Test
    public void testMy() {
        String[] a = {"df", "dd", "ss"};
        List<String> ids = Arrays.asList(a);
        for (String id : ids) {
            log.info(id);
        }
    }

    @Test
    public void asString() {
        String[] a = {"df", "dd", "ss"};
        List<String> ids = Arrays.asList(a);
        StringBuilder sb = new StringBuilder();
        if (ids != null && ids.size() > 0) {
            for (int i = 0; i < ids.size(); i++) {
                sb.append(ids.get(i));
                if (i < ids.size() - 1) {
                    sb.append(",");
                }
            }
            log.info("---------" + sb.toString());
        }
    }
}
