package com.fmi.tourguideassistant.servletfilters;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseAuthException;
import com.google.firebase.auth.UserRecord;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class FireBaseTokenAuthenticationProvider implements AuthenticationProvider {
    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        if (!supports(authentication.getClass())) {
            return null;
        }

        FireBaseAuthenticationToken firebaseAuthToken = (FireBaseAuthenticationToken) authentication;
        UserRecord userRecord = null;
        try {
            userRecord = FirebaseAuth.getInstance().getUser(firebaseAuthToken.getPrincipal().toString());
            Map<String, Object> claims = new HashMap<>();
            userRecord.getCustomClaims().forEach((k, v) -> claims.put(k, v));
            if(userRecord.getEmail().contains("admin") && !userRecord.getCustomClaims().containsKey("ROLE_ADMIN")){
                if (!claims.containsKey("ROLE_ADMIN")) {
                    claims.put("ROLE_ADMIN", true);
                }
                FirebaseAuth.getInstance().setCustomUserClaims(userRecord.getUid(), claims);
            }
        } catch (FirebaseAuthException e) {
            e.printStackTrace();
        }

        return firebaseAuthToken;
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return (FireBaseAuthenticationToken.class.isAssignableFrom(authentication));
    }
}
