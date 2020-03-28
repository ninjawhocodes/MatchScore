package com.micro.datagetter.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.micro.datagetter.service.GetDataService;
import com.micro.datagetter.vo.MatchDataVO;

@RestController
@RequestMapping("api")
public class DataGetterController {

	@Autowired
	GetDataService getDataService;

	@GetMapping("/cricketScore/{matchId}")
	public MatchDataVO getMatchData(@PathVariable Long matchId) {
		return getDataService.getMatchData(matchId);	
	}
}
