package com.micro.datagetter.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class MatchDataVO implements Serializable {

	private static final long serialVersionUID = -6571326142483899912L;

	@JsonProperty("Team-1")
	private String teamOne;
	
	@JsonProperty("Team-2")
	private String teamTwo;
	
	@JsonProperty("Winning team’s score")
	private String score;
	
	@JsonProperty("Round rotation")
	private String roundRotation;

}
