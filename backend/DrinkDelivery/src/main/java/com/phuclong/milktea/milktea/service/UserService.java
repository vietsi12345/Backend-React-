package com.phuclong.milktea.milktea.service;

import com.phuclong.milktea.milktea.model.User;

public interface UserService {
    public User findUserByJwtToken(String jwt) throws Exception;
    public User findUserByEmail(String email) throws Exception;
}
