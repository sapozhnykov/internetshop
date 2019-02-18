package com.sapozhnykov.services.authorization;

public interface AuthClientService {
    boolean singIn(String phone, String password);
    boolean isLoginExists(String phone);
    boolean register(long clientId, String phone, String password);
}
