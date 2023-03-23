package pl.us.spring.quizapp.dto;

import lombok.Builder;

import java.io.Serializable;

@Builder
public class RequestUserDto implements Serializable {
    public String name;
    public String password;
    public String email;
}
