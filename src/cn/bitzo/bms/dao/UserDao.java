package cn.bitzo.bms.dao;

import cn.bitzo.bms.entity.User;

import java.util.List;

public interface UserDao {
    public User queryUserByUsername(String username);
    public User queryUserById(Integer id);
    public List<User> queryAllUsers(User user);
    public Integer countAllUsers(User user);
    public Integer updateUserInfo(User user);
    public Integer addUser(User user);
}
