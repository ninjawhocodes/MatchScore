package com.micro.datagetter.service;

import com.micro.datagetter.vo.MatchDataVO;

public interface GetDataService {
	MatchDataVO getMatchData(Long matchId);
}
