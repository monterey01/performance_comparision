package com.anant.performance.demo.contoller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

@Controller
public class TestController {

	@Autowired
	BubbleSort bubbleSort;

	@RequestMapping(value = "/long-operation", headers = "Accept=application/json")
	@ResponseBody
	public String longOperation() {
		System.out.println("hello");
		bubbleSort.runBubbleSort();
		return "{\"message\":\"done\"}";
	}

}
