package com.muye.kl.demo.utils;

import lombok.extern.slf4j.Slf4j;

import java.util.List;

/**
 * @author : gwh
 * @date : 2020-01-13 14:34
 **/
@Slf4j
public class StringUtils {

    /**
     * list 转换为以","分隔的字符串
     * @param ids
     * @return
     */
    public static String asString( List<String> ids) {
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
        return sb.toString();
    }

}
