package com.inoriii.hello.spring.common.utils;

/**
 * @author sakura
 * @date: 2022/6/28 22:14
 * @description:
 */
public class PagerHelper {
    public static long fetchOffset(long page, long size) {
        if (page < 1) {
            page = 1;
        }
        if (size < 1) {
            size = 0;
        }
        return (page - 1) * size;
    }
}
