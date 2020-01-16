package com.muye.kl.demo.entity;

import java.sql.Timestamp;

import javax.validation.constraints.NotEmpty;

import org.springframework.format.annotation.DateTimeFormat;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * 数据源信息 bds_datasource_info
 * 
 * @author wanglei
 * @date 2019/10/19
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class DataSourceInfo{

	/**
	 * 数据源主键 不能为空
	 */
	private String datasourceId;

	/**
	 * 数据源类型
	 */
	@NotEmpty
	private String datasourceType;

	/**
	 * 数据源名称
	 */
	private String datasourceName;

	/**
	 * 用户名 不能为空
	 */
	@NotEmpty(message = "数据源用户名不允许为空")
	private String username;

	/**
	 * 密码 不能为空
	 */
	@NotEmpty(message = "数据源密码不允许为空")
	private String password;

	/**
	 * 数据源链接 不能为空
	 */
	@NotEmpty(message = "数据源连接url不允许为空")
	private String url;

	/**
	 * 数据源驱动类 不能为空
	 */
	@NotEmpty(message = "数据源驱动类不允许为空")
	private String driver;

	/**
	 * 数据源描述
	 */
	private String datasourceDesc;

	/**
	 * 创建时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Timestamp createTime;

	/**
	 * 修改时间
	 */
	@DateTimeFormat(pattern = "yyyy-MM-dd HH:mm:ss")
    @JsonFormat(pattern = "yyyy-MM-dd HH:mm:ss", timezone="GMT+8")
	private Timestamp updateTime;

	/**
	 * 创建用户
	 */
	private String createUser;

	/**
	 * 修改用户
	 */
	private String updateUser;

	/**
	 * 数据源连接池的配置
	 */
	private String datasourcePoolCfgId;
	
}
