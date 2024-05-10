package com.mysite.sbb.Daelim;

import java.util.List;

import org.apache.logging.log4j.Logger;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import lombok.RequiredArgsConstructor;
@ComponentScan
@RequestMapping("/daelim")
@RequiredArgsConstructor
@Controller
public class DaelimController {
	static Logger logger;
	
	private final DaelimRepository daelimRepository;
	
	@GetMapping("/list")
	public String list(Model model) {
    	List<DaelimVO> list = this.daelimRepository.findAllFromDbTest();
        model.addAttribute("list", list);	
        return "home";
    }
	
}
