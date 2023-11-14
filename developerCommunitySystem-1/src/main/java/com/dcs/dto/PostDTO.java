package com.dcs.dto;


import java.time.LocalDateTime;
import java.util.List;

import javax.validation.constraints.NotNull;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class PostDTO {
@NotNull
	private Integer postId;
	private String query;
	private LocalDateTime postDateTime;
	private String topic;
	private DeveloperDTO developer;
	private List<ResponseDTO> listOfResponse;
	private Integer noOfViews;
	
}