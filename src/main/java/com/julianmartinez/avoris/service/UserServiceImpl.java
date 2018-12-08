package com.julianmartinez.avoris.service;

import com.julianmartinez.avoris.model.User;
import com.julianmartinez.avoris.model.messages.UserRQ;
import com.julianmartinez.avoris.model.messages.UserRS;
import com.julianmartinez.avoris.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class UserServiceImpl implements UserService{

    @Autowired
    UserRepository userRepository;

    @Override
    public UserRS addUser(UserRQ request) {
        long id = userRepository.addUser(request.getName());
        User user = userRepository.getUserById(id);
        return UserRS.from(user);
    }

    @Override
    public List<UserRS> getUserList() {
        List<User> userList = userRepository.getUserList();
        return userList.stream().map(user -> UserRS.from(user)).collect(Collectors.toList());
    }

    @Override
    public UserRS getUserById(long id) {
        User user = userRepository.getUserById(id);
        return UserRS.from(user);
    }

    @Override
    public void modifyUserById(UserRQ request) {
        userRepository.modifyUserById(request.getId(), request.getName());
    }

    @Override
    public void removeUserById(long id) {
        userRepository.removeUserById(id);
    }
}
