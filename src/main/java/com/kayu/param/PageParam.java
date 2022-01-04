package com.kayu.param;

import lombok.Data;

/**
 * Created by Jin.Z.J  2022/1/4
 */
@Data
public class PageParam {

    /**
     * 页码
     */
    private Integer pageNow;

    /**
     * 每页条数
     */
    private Integer pageSize = 20;

}
