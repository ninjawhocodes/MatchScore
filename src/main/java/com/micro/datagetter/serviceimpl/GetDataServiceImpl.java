package com.micro.datagetter.serviceimpl;

import java.util.HashMap;
import java.util.Map;

import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import com.micro.datagetter.service.GetDataService;
import com.micro.datagetter.vo.DataVO;
import com.micro.datagetter.vo.MatchDataVO;




@Service
public class GetDataServiceImpl implements GetDataService{

	final String uri = "http://cricapi.com/api/cricketScore";
	
	@Override
	public MatchDataVO getMatchData(Long matchId) {
		String finalUri = uri + "?unique_id=" + matchId;
		RestTemplate restTemplate = new RestTemplate();
        final HttpHeaders headers = new HttpHeaders();
        headers.set("apikey", "WmPJrX2s3KMyZVPFwlm1vxXLXKw1");

        final HttpEntity<String> entity = new HttpEntity<String>(headers);
        
        ResponseEntity<DataVO> matchScore = restTemplate.exchange(finalUri, HttpMethod.GET, entity, DataVO.class);        
        DataVO matchScoreObj = matchScore.getBody();
        
        MatchDataVO result = new MatchDataVO();
        Map<String,String> scoreAndTeam = GetDataServiceImpl.getScore(matchScoreObj);
        
        if (scoreAndTeam.get("winner").equals("1")) {
        	result.setTeamOne(matchScoreObj.getTeamOne() + "(Winner)");
        	result.setTeamTwo(matchScoreObj.getTeamTwo());
        } else if (scoreAndTeam.get("winner").equals("2")) {
        	result.setTeamTwo(matchScoreObj.getTeamTwo() + "(Winner)");
        	result.setTeamOne(matchScoreObj.getTeamOne());
        } else {
        	result.setTeamOne(matchScoreObj.getTeamTwo() + "(Draw)");
        	result.setTeamTwo(matchScoreObj.getTeamOne() + "(Draw)");
        }
        
        result.setScore(scoreAndTeam.get("score"));
        result.setRoundRotation(GetDataServiceImpl.getRoundRotation(result.getScore()));
        return result;
	}
	
	
	private static Map<String,String> getScore(DataVO matchScoreObj) {
		Map<String,String> formattedMap = new HashMap<>();

		String s = matchScoreObj.getScore().replace("*", "");
		String team1 = matchScoreObj.getTeamOne();
		String team2 = matchScoreObj.getTeamTwo();

		String[] teamsScoreArray = s.split(" v ");

		String team1Score = teamsScoreArray[0].contains(team1) ? teamsScoreArray[0].trim().replace(team1, "").trim()
				: teamsScoreArray[1].trim().replace(team1, "").trim();

		String team2Score = teamsScoreArray[0].contains(team2) ? teamsScoreArray[0].trim().replace(team2, "").trim()
				: teamsScoreArray[1].trim().replace(team2, "").trim();

		int runsScoredByTeam1 = Integer
				.parseInt(team1Score.contains("/") ? team1Score.substring(0, (team1Score.indexOf("/")))
						: team1Score.substring(0, team1Score.length()));

		int runsScoredByTeam2 = Integer
				.parseInt(team2Score.contains("/") ? team2Score.substring(0, (team2Score.indexOf("/")))
						: team2Score.substring(0, team2Score.length()));

		if (runsScoredByTeam1 == runsScoredByTeam2) {
			formattedMap.put("winner", "0");
			formattedMap.put("score", team1Score);
		}
		else if (runsScoredByTeam1 > runsScoredByTeam2) {	
			formattedMap.put("winner", "1");
			formattedMap.put("score", team1Score);
		} else {
			formattedMap.put("winner", "2");
			formattedMap.put("score", team2Score);
		}
		
	
		return formattedMap;
	}
	
	private static String getRoundRotation(String winningScore) {
		String[] splitted = winningScore.split("/");
		return splitted[1] + splitted[0] + "/";
	}

}
