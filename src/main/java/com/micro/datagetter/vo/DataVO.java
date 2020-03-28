package com.micro.datagetter.vo;

import java.io.Serializable;

import com.fasterxml.jackson.annotation.JsonProperty;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
@Getter
@Setter
@NoArgsConstructor
public class DataVO implements Serializable {
	private static final long serialVersionUID = 229643216706995510L;

	@JsonProperty("team-1")
	private String teamOne;
	
	@JsonProperty("team-2")
	private String teamTwo;
	
	@JsonProperty("score")
	private String score;
	
}
