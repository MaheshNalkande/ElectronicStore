package com.project.ecom.dtos;

import lombok.Builder;
import lombok.Data;
import org.springframework.http.HttpStatus;

@Data
@Builder
public class API_Response_MSG {
    private String message;
    private boolean success;
    private HttpStatus status;

}
