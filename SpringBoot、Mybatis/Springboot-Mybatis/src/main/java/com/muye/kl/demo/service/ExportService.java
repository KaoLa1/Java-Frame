package com.muye.kl.demo.service;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.sql.SQLException;
import java.util.Map;

public interface ExportService {

    void exportExcel(HttpServletResponse response, Map<String, Object> exportMsg) throws IOException, SQLException;

    void export(HttpServletRequest request, HttpServletResponse response, Map<String, Object> exportMsg) throws Exception;
}
