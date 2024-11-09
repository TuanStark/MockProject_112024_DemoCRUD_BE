package com.example.mockproject_112024_democrud_be.helper.response;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
@Builder
public class ResponseObject <T> {
    private String message;
    private int code;
    private T data;
}
