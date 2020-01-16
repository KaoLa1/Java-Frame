package com.muye.kl.demo.service;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

/**
 * @author : gwh
 * @date : 2020-01-06 15:02
 **/
public interface PoiService {

    /**
     * 导出Excel
     *  此方法为在文件后加后缀，生成的文件看起来是Excel，其实POI无法解析，但可用（可以尝试生成其他文件格式）
     * 1、可以导出某个数据源中，某张表的某些字段
     * 2、支持大量数据的导出
     * @param response
     * @param exportMsg
     * @return
     * @throws IOException
     * @throws SQLException
     */
    int exportExcel(HttpServletResponse response, Map<String,Object> exportMsg) throws IOException, SQLException;

    /**
     * 导入Excel
     * 根据导出的模板导入
     *
     * @param importMsg
     * @return
     * @throws Exception
     */
    int importExcel(Map<String, Object> importMsg) throws Exception;
}
