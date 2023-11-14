package com.dcs.dto;
import java.time.LocalDate;
import java.util.List;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;
@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
public class DeveloperDTO extends UserDTO {
	//@NotNull
	private String devName;
	private String devSkill;
	private LocalDate memberSince;
	private Integer reputation;
	private String status;
	private List<PostDTO> listOfPosts;
	
	

}