package org.weblr.controller;

import java.sql.SQLException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.mchange.v2.c3p0.ComboPooledDataSource;

@Controller
@RequestMapping(value="test")
public class TestController {
	@Autowired
	private ComboPooledDataSource combopooled;
	
	@RequestMapping(value="/ss" ,method=RequestMethod.GET)
	@ResponseBody public void test(){
		System.out.println("hello word");
		try {
			System.out.println(combopooled.getConnection());
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
