package com.struts.action;

import com.opensymphony.xwork2.ActionSupport;

public class HelloStrutsAction extends ActionSupport {
	@Override
	public String execute() throws Exception {
		return "success";

	}

}
