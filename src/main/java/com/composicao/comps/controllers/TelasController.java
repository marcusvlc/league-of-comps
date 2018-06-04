package com.composicao.comps.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
@CrossOrigin
public class TelasController {
	
	@RequestMapping("/")
	public String principal() {
		return "index";
	}

}
