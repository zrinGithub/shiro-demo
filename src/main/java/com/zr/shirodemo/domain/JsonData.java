package com.zr.shirodemo.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Description:
 * 返回类
 *
 * @author zhangr
 * 2020/1/10 10:48
 * }
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class JsonData<T> {
    private Integer code; // 状态码 0 表示成功，1表示处理中，-1表示失败 -3没有权限
    private T data; // 数据
    private String msg;// 描述
}
