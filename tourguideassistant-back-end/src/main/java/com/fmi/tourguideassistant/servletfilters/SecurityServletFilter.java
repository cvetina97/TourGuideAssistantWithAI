package com.fmi.tourguideassistant.servletfilters;

import com.google.api.gax.rpc.StatusCode;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.FirebaseToken;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

@Component
public class SecurityServletFilter extends OncePerRequestFilter {

    @Autowired
    FireBaseTokenAuthenticationProvider fireBaseTokenAuthenticationProvider;

    @Override
    protected void doFilterInternal(HttpServletRequest request, HttpServletResponse response, FilterChain chain) throws IOException {
        String token = getBearerToken(request);
        try {
            if(token != null){
                FirebaseToken decodedToken = null;
                Authentication auth = null;
                try {
                    decodedToken = FirebaseAuth.getInstance().verifyIdToken(token);
                    auth = getAuthentication(decodedToken);
                } catch (FirebaseAuthException e) {
                    e.printStackTrace();
                    response.sendError(Integer.parseInt(e.getErrorCode()), e.getMessage());
                }

                SecurityContextHolder.getContext().setAuthentication(auth);
            }
            chain.doFilter(request, response);
        } catch (IOException | ServletException e) {
            e.getMessage();
            SecurityContextHolder.clearContext();
            response.sendError(response.getStatus(), e.getMessage());
        }
    }

    private Authentication getAuthentication(FirebaseToken token) throws FirebaseAuthException {
        List<SimpleGrantedAuthority> authorities = new ArrayList<>();
        if(token.getEmail().contains("admin")){
            authorities.add(new SimpleGrantedAuthority("ROLE_ADMIN"));
        }
        Authentication auth = new FireBaseAuthenticationToken(token.getUid(), token, authorities);
        return fireBaseTokenAuthenticationProvider.authenticate(auth);
    }

    private String getBearerToken(HttpServletRequest request) {
        String bearerToken = null;
        String authorization = request.getHeader("Authorization");
        if (StringUtils.hasText(authorization) && authorization.startsWith("Bearer ")) {
            bearerToken = authorization.substring(7);
        }
        return bearerToken;
    }
}
