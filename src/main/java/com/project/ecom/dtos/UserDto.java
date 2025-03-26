package com.project.ecom.dtos;

import jakarta.persistence.Column;
import jakarta.persistence.Id;
import lombok.Builder;
import lombok.Data;

@Data

public class UserDto {

    private String userId;

    private String name;

    private String email;

    private String password;

    private String gender;

    private String about;
}
