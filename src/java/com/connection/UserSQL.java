package com.connection;

import com.models.User;

public interface UserSQL {
    public void setupClient(User user);
    public boolean isClientExist(String login, String password);
}
