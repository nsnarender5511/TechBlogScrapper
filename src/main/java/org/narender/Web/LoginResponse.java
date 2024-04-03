package org.narender.Web;

import java.io.Serializable;

public class LoginResponse implements Serializable {
    private String token;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }
}
