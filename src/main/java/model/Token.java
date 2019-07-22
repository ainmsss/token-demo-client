package model;


import java.util.UUID;

public class Token {

    private String client_id;

    private String access_token;

    private Integer expires_in;

    private String error;

    public String getClient_id() {
        return client_id;
    }

    public void setClient_id(String client_id) {
        this.client_id = client_id;
    }

    public String getAccess_token() {
        return access_token;
    }

    public void setAccess_token(String access_token) {
        this.access_token = access_token;
    }

    public Integer getExpires_in() {
        return expires_in;
    }

    public void setExpires_in(Integer expires_in) {
        this.expires_in = expires_in;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }

    public Token() {

    }

    public Token(String client_id) {
        this.client_id = client_id;
        this.access_token = UUID.randomUUID().toString().replace("-", "");
        this.expires_in = 3600;
    }
}
