package com.julianmartinez.avoris.service;

import com.julianmartinez.avoris.model.messages.UserRQ;
import com.julianmartinez.avoris.model.messages.UserRS;

import java.util.List;

public interface UserService {

    UserRS addUser(UserRQ request);
    List<UserRS> getUserList();
    UserRS getUserById(long id);
    void modifyUserById(UserRQ request);
    void removeUserById(long id);

}
