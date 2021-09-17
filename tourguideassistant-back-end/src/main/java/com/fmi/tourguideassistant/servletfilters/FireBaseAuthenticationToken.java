package com.fmi.tourguideassistant.servletfilters;

import com.google.firebase.auth.FirebaseToken;
import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.util.Assert;

import java.util.Collection;

public class FireBaseAuthenticationToken extends AbstractAuthenticationToken {

    private Object uid;
    private Object token;

    public FireBaseAuthenticationToken(String uid, FirebaseToken token) {
        super((Collection)null);
        this.uid = uid;
        this.token = token;
        this.setAuthenticated(false);
    }

    public FireBaseAuthenticationToken(String uid, FirebaseToken token, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.uid = uid;
        this.token = token;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return this.token;
    }

    @Override
    public Object getPrincipal() {
        return this.uid;
    }

    public void setAuthenticated(boolean isAuthenticated) throws IllegalArgumentException {
        Assert.isTrue(!isAuthenticated, "Cannot set this token to trusted - use constructor which takes a GrantedAuthority list instead");
        super.setAuthenticated(false);
    }

    public void eraseCredentials() {
        super.eraseCredentials();
        this.token = null;
    }
}
