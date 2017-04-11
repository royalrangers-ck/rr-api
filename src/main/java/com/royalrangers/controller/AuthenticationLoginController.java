package com.royalrangers.controller;

import com.royalrangers.dto.ResponseResult;
import com.royalrangers.dto.jwt.JwtAuthenticationRequest;
import com.royalrangers.dto.jwt.JwtAuthenticationResponse;
import com.royalrangers.enums.Messages;
import com.royalrangers.service.JwtUserDetailsServiceImpl;
import com.royalrangers.service.LoginService;
import com.royalrangers.utils.ResponseBuilder;
import com.royalrangers.utils.security.JwtTokenUtil;
import com.royalrangers.utils.security.JwtUser;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.mobile.device.Device;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;

@RestController
@Slf4j
public class AuthenticationLoginController {

    @Value("${jwt.header}")
    private String tokenHeader;

    @Autowired
    private AuthenticationManager authenticationManager;

    @Autowired
    private JwtTokenUtil jwtTokenUtil;

    @Autowired
    private JwtUserDetailsServiceImpl userDetailsService;

    @Autowired
    private LoginService loginService;

    @RequestMapping(value = "/auth", method = RequestMethod.POST)
    public ResponseResult createAuthenticationToken(@RequestBody JwtAuthenticationRequest authenticationRequest, Device device) throws AuthenticationException {

        Messages messages = loginService.checkLoginInformation(authenticationRequest.getEmail());
        if (messages != null)
            return ResponseBuilder.fail(messages.getMessage());

        // Perform the security
        final Authentication authentication = authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(
                        authenticationRequest.getEmail(),
                        authenticationRequest.getPassword()
                )
        );
        SecurityContextHolder.getContext().setAuthentication(authentication);

        // Reload password post-security so we can generate token
        final UserDetails userDetails = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        final String token = jwtTokenUtil.generateToken(userDetails, device);
        log.info("generate token for user " + authenticationRequest.getEmail());

        // Return the token
        return ResponseBuilder.success(new JwtAuthenticationResponse(token));
    }

    @RequestMapping(value = "/refresh", method = RequestMethod.GET)
    public ResponseEntity<?> refreshAndGetAuthenticationToken(HttpServletRequest request) {
        String token = request.getHeader(tokenHeader);
        String username = jwtTokenUtil.getUsernameFromToken(token);
        JwtUser user = (JwtUser) userDetailsService.loadUserByUsername(username);

        if (jwtTokenUtil.canTokenBeRefreshed(token, user.getLastPasswordResetDate())) {
            String refreshedToken = jwtTokenUtil.refreshToken(token);
            return ResponseEntity.ok(ResponseBuilder.success(new JwtAuthenticationResponse(refreshedToken)));
        } else {
            return ResponseEntity.badRequest().body(ResponseBuilder.fail("Token can't be refreshed."));
        }
    }
}
