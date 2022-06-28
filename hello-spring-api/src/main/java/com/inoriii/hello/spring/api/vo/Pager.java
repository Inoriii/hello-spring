package com.inoriii.hello.spring.api.vo;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;

import java.util.List;

/**
 * @author sakura
 * @date: 2022/6/28 21:54
 * @description:
 */
@Data
@Builder
@AllArgsConstructor
public class Pager<T> {
    /**
     * 总记录数
     */
    private long total;
    /**
     * 页数
     */
    private long page;
    /**
     * 页数据量
     */
    private long size;
    /**
     * 返回的记录
     */
    private List<T> data;
}