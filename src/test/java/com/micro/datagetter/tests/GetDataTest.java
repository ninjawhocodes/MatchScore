package com.micro.datagetter.tests;

import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.verify;

import org.junit.jupiter.api.Test;

import com.micro.datagetter.service.GetDataService;
import com.micro.datagetter.vo.MatchDataVO;

public class GetDataTest {
		
	
	@Test
	public void TestMock () {
		MatchDataVO matchDataVO = new MatchDataVO();
		GetDataService mockGetDataService = mock(GetDataService.class);
		given(mockGetDataService.getMatchData(1234L)).willReturn(matchDataVO);
		mockGetDataService.getMatchData(1234L);
		verify(mockGetDataService).getMatchData(1234L);

	}
}
