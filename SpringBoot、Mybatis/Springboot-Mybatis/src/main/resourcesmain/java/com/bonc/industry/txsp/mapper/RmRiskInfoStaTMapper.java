package com.bonc.industry.txsp.mapper;

import java.util.List;

import com.bonc.industry.txsp.entity.RmRiskInfoStaT;


/**
 * 	myBatis Mapper类
 * 	??????Ϣͳ?Ʊ
 * 
 * @author admin
 * @email admin@bonc.com.cn
 * @date 2020-03-19 15:36:00
 */
public interface RmRiskInfoStaTMapper {
	
	/**
	 * 	查询RmRiskInfoStaT列表
	 * 
	 * @return List<RmRiskInfoStaT>
	 * @param rmRiskInfoStaT  rmRiskInfoStaT
	 * @param pageSize  pageSize
	 * @param pageNum  pageNum
	 * @author admin
	 * @date 2020-03-19 15:36:00
	 */
	List<RmRiskInfoStaT> query(RmRiskInfoStaT rmRiskInfoStaT, Integer pageSize, Integer pageNum);
	
	/**
	 * 	查询记录
	 * 
	 * @return RmRiskInfoStaT
	 * @param statisticId	 
	 * @author admin
	 * @date 2020-03-19 15:36:00
	 */
	RmRiskInfoStaT findById(String statisticId);
	
	/**
	 * 	新增记录
	 * 
	 * @return RmRiskInfoStaT
	 * @param rmRiskInfoStaT
	 * @author admin
	 * @date 2020-03-19 15:36:00
	 */
	int create(RmRiskInfoStaT rmRiskInfoStaT);
	
	/**
	 * 	更新记录
	 * 
	 * @return RmRiskInfoStaT
	 * @param rmRiskInfoStaT
	 * @author admin
	 * @date 2020-03-19 15:36:00
	 */
	int update(RmRiskInfoStaT rmRiskInfoStaT);
	
	/**
	 * 	删除记录
	 * 
	 * @return RmRiskInfoStaT
	 * @param  statisticId	  
	 * @author admin
	 * @date 2020-03-19 15:36:00
	 */
	int deleteById(String statisticId);

    /**
     * 批量删除记录
     * @param serviceIds
     * @return
     */
    int deleteBatch(String[] statisticIds});
}
