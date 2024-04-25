package com.example.rest_apis.payloads;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class JwtAuthenticationResponse {

    private String token;
}
