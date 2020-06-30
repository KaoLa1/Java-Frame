package com.bonc.industry.txsp.entity;

import java.io.Serializable;
import java.util.Date;
import javax.persistence.*;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.experimental.Accessors;


/**
 * 	实体类
 * ??????Ϣͳ?Ʊ
 * 
 * @author admin
 * @email admin@bonc.com.cn
 * @date 2020-03-19 15:36:00
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Accessors(chain = true)
public class RmRiskInfoStaT implements Serializable {
	private static final long serialVersionUID = 1L;
	
	/**
	* ͳ??????ID
	*/
    private String statisticId;
	
	/**
	* ͳ?Ʒ?ʽ
	*/
    private String statisticType;
	
	/**
	* ͳ??????CODE
	*/
    private String statisticCategoryCode;
	
	/**
	* ͳ?????
	*/
    private String statisticCategoryValue;
	
	/**
	* ͳ?????
	*/
    private Date statisticDate;
	
	/**
	* ͳ????????
	*/
    private BigDecimal statisticData;
	


}
