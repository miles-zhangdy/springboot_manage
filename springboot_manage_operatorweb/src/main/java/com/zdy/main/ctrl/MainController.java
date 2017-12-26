package com.zdy.main.ctrl;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import com.zdy.util.BaseController;

@Controller
public class MainController extends BaseController {

	@RequestMapping(value = { "/index", "/toindex", "/" })
	public String toIndex() {
		return "index";
	}

}
