package com.muye.kl.demo.entity.BO;

import io.swagger.annotations.ApiModel;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.RequiredArgsConstructor;

/**
 * @author： GuoFeng
 * Describe :
 * Created on 2019/12/27
 * Modified By :
 */
@Data
@AllArgsConstructor
@RequiredArgsConstructor
@Builder
@ApiModel("数据库信息")
public class Information {

    private String ip;
    private String port;
    private String databaseName;

}
