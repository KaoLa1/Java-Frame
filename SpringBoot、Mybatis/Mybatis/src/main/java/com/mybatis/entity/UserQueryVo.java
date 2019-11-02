package com.mybatis.entity;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.List;

/**
 * <h3>Mybatis</h3>
 * <p>封装类型</p>
 *
 * @author : gwh
 * @date : 2019-10-22 16:31
 **/

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UserQueryVo {

    //在这里包装需要查询的条件
    private List<Integer> ids;

    //用户查询条件
    private UserCustom userCustom;

}
