package com.dcs.dto;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Data
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class UserDTO {
	 @NotNull
	private Integer userId;
	 @NotNull
	private String userName;
	 @Min(5)
	 @Max(10)
	private String userPassword;
	private String userRole;	
}
 