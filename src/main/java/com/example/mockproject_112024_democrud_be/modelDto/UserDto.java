package com.example.mockproject_112024_democrud_be.modelDto;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class UserDto {
	private String name;
	
	private String yearOfBirth;
	private String phone;

	private String email;

	private String address;
}
