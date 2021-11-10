package com.example.finalproject.security;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

@RestController
@CrossOrigin(origins = "*")
public class JwtAuthenticationController {
    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private com.example.todo.security.JwtTokenUtil jwtTokenUtil;

    @Autowired
    private com.example.todo.security.JwtUserDetailsService jwtUserDetailsService;

    @RequestMapping(value = "/authenticate",method = RequestMethod.POST)
    public ResponseEntity<?> createAuthenticationToken(@RequestBody JwtRequest authenticationRequest){
        authentiate(authenticationRequest.getUsername(),authenticationRequest.getPassword());
        final UserDetails userDetails=jwtUserDetailsService.
                loadUserByUsername(authenticationRequest.getUsername());
        final String token=jwtTokenUtil.generateToken(userDetails);
        return ResponseEntity.ok(new JwtResponse(token));
    }

    private void authentiate(String username,String password){
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(username,password));
    }
}
