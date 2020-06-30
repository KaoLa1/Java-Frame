package com.bonc.industry.txsp.service;


import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;

import com.bonc.industry.txsp.entity.RmRiskInfoStaT;
import com.bonc.industry.txsp.mapper.RmRiskInfoStaTMapper;

/**
 * 	服务层类
 * ??????Ϣͳ?Ʊ
 *
 * @author admin
 * @email admin@bonc.com.cn
 * @date 2020-03-19 15:36:00
 */
@Service
public class RmRiskInfoStaTService{

	private static final Logger logger = LoggerFactory.getLogger(RmRiskInfoStaTService.class);
	
	@Autowired
	private RmRiskInfoStaTMapper rmRiskInfoStaTMapper;
	
	
	public PageInfo<RmRiskInfoStaT> query(RmRiskInfoStaT rmRiskInfoStaT, Integer pageSize, Integer pageNum) {
		
		if (pageNum ==null || pageNum.equals("")){
            pageNum =1;
        }
        if (pageSize ==null || pageSize.equals("")){
            pageSize =10;
        }
        //开启分页
        PageHelper.startPage(pageNum, pageSize);
		List<RmRiskInfoStaT> list = rmRiskInfoStaTMapper.query(rmRiskInfoStaT, pageSize, pageNum);
		logger.info("queryRmRiskInfoStaT=> effect line count is " + list.size());
		return new PageInfo<>(list);
	}
	
	public RmRiskInfoStaT findById(String statisticId) {
		return rmRiskInfoStaTMapper.findById(statisticId);
	}
	
	
	public int create(RmRiskInfoStaT rmRiskInfoStaT) {
		return rmRiskInfoStaTMapper.create(rmRiskInfoStaT);
	}
	
	
	public int update(RmRiskInfoStaT rmRiskInfoStaT) {
		return rmRiskInfoStaTMapper.update(rmRiskInfoStaT);
	}
	
	
	public int deleteById(String statisticId) {
		return rmRiskInfoStaTMapper.deleteById(statisticId);
	}

    public int deleteBatch(String serviceIds){
        if(serviceIds == null){
            return 0;
        }
        String[] sIds = serviceIds.split(",");
        if(sIds.length > 0){
            bdsServiceInfoMapper.deleteBatch(sIds);
            return 1;
        }
        return 0;
    }
}