package com.kayu.result;

import lombok.Data;

import java.util.List;

/**
 * Created by Jin.Z.J  2022/1/4
 */
@Data
public class PageData<T> {


    /**
     * 总条数
     */
    private Long total;
    /**
     * 总页数
     */
    private Long pages;
    /**
     * 列表
     */
    private List<T> list;


}
