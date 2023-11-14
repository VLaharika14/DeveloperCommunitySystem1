package com.dcs.dto;


import javax.validation.constraints.NotNull;

import com.dcs.entity.Response;
import com.dcs.util.VoteType;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class VoteDTO {
	@NotNull
	private Integer voteId;
	private VoteType voteType;
	private DeveloperDTO developerWhoVoted;
	private Response response;
	
}