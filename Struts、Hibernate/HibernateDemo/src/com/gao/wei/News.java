package com.gao.wei;

import java.util.Date;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;

public class News {
	private Integer id;
	private String title;
	private String content;
	private Date date;
	public Integer getId() {
		return id;
	}
	public void setId(Integer id) {
		this.id = id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Date getDate() {
		return date;
	}
	public void setDate(Date date) {
		this.date = date;
	}
	
	public News() {
		// TODO 自动生成的构造函数存根
	}
	
	public News(String title,String content,Date date) {
		super();
		this.title=title;
		this.content=content;
		this.date=date;
	}
	
	@Override
	public String toString(){
		return "News [id="+id+",title="+title+",content="+",date="+date+"]";
	}
	

}
