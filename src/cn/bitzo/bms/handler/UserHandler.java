package cn.bitzo.bms.handler;

import cn.bitzo.bms.entity.MyResponse;
import cn.bitzo.bms.entity.User;
import cn.bitzo.bms.service.UserService;
import cn.bitzo.bms.utils.TokenUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

@Controller
public class UserHandler {

    @Autowired
    private UserService userService;

    @RequestMapping(value = "/login")
    @ResponseBody
    public MyResponse login(@RequestBody User user) {
        MyResponse myResponse = new MyResponse(200, false, "登录失败", null);
        boolean isSuccess = userService.login(user.getUsername(), user.getPassword());
        if (isSuccess) {
            User user1 = userService.queryUserByUsername(user.getUsername());
            // 登录成功获取token
            TokenUtil tokenUtil = TokenUtil.getInstance();
            String token = tokenUtil.getToken(user.getUsername());
            user1.setToken(token);
            // 配置返回的数据项
            myResponse.setData(user1);
            myResponse.setMsg("登录成功");
            myResponse.setStatus(200);
            myResponse.setIsSuccess(true);
            return myResponse;
        }
        return myResponse;
    }

    @RequestMapping(value = "/api/users/getAllUsers")
    @ResponseBody
    public MyResponse queryAllUsers(int page) {
        MyResponse myResponse = new MyResponse(200, false, "查询失败", null);
        List<User> users = userService.queryUsers(null);
        myResponse.setIsSuccess(true);
        myResponse.setData(users);
        myResponse.setMsg("查询成功");
        return myResponse;
    }

    @RequestMapping(value = "/api/users/count")
    @ResponseBody
    public MyResponse countAllUsers(User user) {
        MyResponse myResponse = new MyResponse(200, false, "查询失败", null);
        int num = userService.countAllUser(user);
        myResponse.setIsSuccess(true);
        myResponse.setData(num);
        myResponse.setMsg("查询成功");
        return myResponse;
    }

    @RequestMapping(value = "/api/users/queryById")
    @ResponseBody
    public MyResponse queryUserById(Integer id) {
        MyResponse myResponse = new MyResponse(200, false, "查询失败", null);
        User user = userService.queryUserById(id);
        myResponse.setIsSuccess(true);
        myResponse.setData(user);
        myResponse.setMsg("查询成功");
        return myResponse;
    }

    @RequestMapping(value = "/api/users/updateUserInfo")
    @ResponseBody
    public MyResponse updateUserInfo(@RequestBody User user) {
        MyResponse myResponse = new MyResponse(200, false, "修改失败", null);
        boolean isSuccess = userService.updateUserInfo(user);
        if (isSuccess) {
            User user1 = userService.queryUserById(user.getId());
            // 配置返回的数据项
            myResponse.setData(user1);
            myResponse.setMsg("修改成功");
            myResponse.setStatus(200);
            myResponse.setIsSuccess(true);
            return myResponse;
        }
        return myResponse;
    }

    @RequestMapping(value = "/api/users/avatar")
    @ResponseBody
    public MyResponse updateAvatar(@RequestParam(value = "id") Integer id, @RequestParam(value = "avatar") MultipartFile avatar, HttpSession session, HttpServletResponse response) throws IOException {
        MyResponse myResponse = new MyResponse(200, false, "修改失败", null);
        // 存储文件并获取文件相对地址
        String path = session.getServletContext().getRealPath("/images/avatar");
        String newFileName = "user_" + id + "_"+ avatar.getOriginalFilename();
        File newFile = new File(path, newFileName);
        avatar.transferTo(newFile);
        // 更新数据库信息
        boolean isSuccess = userService.updateUserInfo(new User(id, "/images/avatar" + "/" + newFileName));
        if (isSuccess) {
            User user1 = userService.queryUserById(id);
            // 配置返回的数据项
            myResponse.setData(user1);
            myResponse.setMsg("修改成功");
            myResponse.setStatus(200);
            myResponse.setIsSuccess(true);
            response.setStatus(200);
            return myResponse;
        }
        return myResponse;
    }

    @RequestMapping(value = "/api/users/addUser")
    @ResponseBody
    public MyResponse addUser(@RequestBody User user) {
        MyResponse myResponse = new MyResponse(200, false, "添加失败", null);
        int num = userService.addUser(user);
        if (num > 0) {
            myResponse.setMsg("添加成功");
            myResponse.setStatus(200);
            myResponse.setIsSuccess(true);
            return myResponse;
        }
        return myResponse;
    }

    @RequestMapping(value = "/api/users/getXls")
    public String testExcel(Map<String, Object> map) {
        List<User> list = new ArrayList<User>();
        List<User> users = userService.queryUsers(null);
        for (int i = 0; i<users.size();++i) {
            list.add(users.get(i));
        }
        map.put("list", list);
        return "userExcelView";
    }
}
