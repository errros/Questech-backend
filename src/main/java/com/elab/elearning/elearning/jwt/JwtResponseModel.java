package com.elab.elearning.elearning.jwt;

import com.elab.elearning.elearning.entity.User;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class JwtResponseModel {
    private String jwtToken;
    private User user;

}
