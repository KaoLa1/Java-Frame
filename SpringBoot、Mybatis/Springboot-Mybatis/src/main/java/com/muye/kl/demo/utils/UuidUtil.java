package com.muye.kl.demo.utils;

import java.util.UUID;

/**
 * @author haj
 * @date 2019/10/31 17:41
 */
public class UuidUtil {
    private UuidUtil(){}

    public static String getRandomUUID() {
        return UUID.randomUUID().toString().replace("-", "");
    }
}
