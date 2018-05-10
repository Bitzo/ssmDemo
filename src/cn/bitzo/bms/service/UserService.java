package cn.bitzo.bms.service;

import cn.bitzo.bms.entity.User;

import java.util.List;

public interface UserService {

    public boolean login(String username, String password);

    public List<User> queryUsers(User user);

    public User queryUserById(Integer id);

    public User queryUserByUsername(String username);

    public Integer countAllUser(User user);

    public Boolean updateUserInfo(User user);

    public int addUser(User user);
}
