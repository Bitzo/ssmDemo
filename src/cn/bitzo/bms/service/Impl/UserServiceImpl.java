package cn.bitzo.bms.service.Impl;

import cn.bitzo.bms.dao.UserDao;
import cn.bitzo.bms.entity.User;
import cn.bitzo.bms.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service(value = "userService")
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Override
    public boolean login(String username, String password) {
        User user = userDao.queryUserByUsername(username);
        if (user == null) return false;
        if (user.getPassword().equals(password))
            return true;
        else return false;
    }

    @Override
    public List<User> queryUsers(User user) {
        List<User> users = userDao.queryAllUsers(user);
        return users;
    }

    @Override
    public User queryUserById(Integer id) {
        User user = userDao.queryUserById(id);
        return user;
    }

    @Override
    public int addUser(User user) {
        int num = userDao.addUser(user);
        if (num > 0) {
            return num;
        }
        return 0;
    }

    @Override
    public User queryUserByUsername(String username) {
        User user = userDao.queryUserByUsername(username);
        return user;
    }

    @Override
    public Integer countAllUser(User user) {
        Integer num = userDao.countAllUsers(user);
        return num;
    }

    @Override
    public Boolean updateUserInfo(User user) {
        int num = userDao.updateUserInfo(user);
        if (num > 0) return true;
        return false;
    }
}
