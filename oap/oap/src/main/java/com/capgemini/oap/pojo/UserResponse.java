package com.capgemini.oap.pojo;
import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class UserResponse {
    private Integer id;
    private String userName;
    private String email;
}
