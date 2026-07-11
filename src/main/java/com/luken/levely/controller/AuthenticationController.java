package com.luken.levely.controller;

import com.luken.levely.dto.request.LoginUserRequestDTO;
import com.luken.levely.dto.request.RegisterUserRequestDTO;
import com.luken.levely.dto.response.LoginUserResponseDTO;
import com.luken.levely.dto.response.RegisterUserResponseDTO;
import com.luken.levely.mapper.UserMapper;
import com.luken.levely.service.AuthenticationService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import java.net.URI;

@RestController
@RequestMapping("/authentication")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationService authenticationService;
    private final UserMapper userMapper;

    @PostMapping(value = "/register")
    public ResponseEntity<RegisterUserResponseDTO> registerUser(@RequestBody RegisterUserRequestDTO body) {
        var user = authenticationService.registerUser(body);

        URI uri = ServletUriComponentsBuilder
                .fromPath("username")
                .buildAndExpand(user.getUsername())
                .toUri();

        return ResponseEntity.created(uri).body(userMapper.toRegisterDTO(user));
    }

    @PostMapping(value = "/login")
    public ResponseEntity<LoginUserResponseDTO> loginUser(@RequestBody LoginUserRequestDTO body) {
        var token = authenticationService.loginUser(body);
        return ResponseEntity.ok().body(new LoginUserResponseDTO(token));
    }

}
